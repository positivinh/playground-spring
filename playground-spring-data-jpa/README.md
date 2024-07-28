# Playground Spring Data JPA

```bash
mvn clean verify -f ../pom.xml -am -pl playground-spring-data-jpa
```

Docker Engine must be running.

## Technologies

- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Auditing](https://docs.spring.io/spring-data/jpa/reference/auditing.html)
- [Testcontainers](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)
  and [Spring Boot integration](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
- [Spring Boot Docker compose support](https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose)
- [Liquibase](https://docs.liquibase.com/start/get-started/liquibase-sql.html)
  and [Spring Boot integration](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/)

# Docker compose support

[Reference documentation](https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose)

## Maven dependency

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Spring Boot configuration

```yaml
spring:
    docker:
        compose:
            enabled: true
            skip:
                in-tests: false
            file: docker-compose.yaml
```

## Docker compose file

```yaml
name: playground-spring-data-jpa
services:
    postgres:
        image: postgres:16-alpine
        ports:
            - 5432:5432
        environment:
            - POSTGRES_USER=playground-user
            - POSTGRES_PASSWORD=password
            - POSTGRES_DB=PLAYGROUND
```

# Liquibase

[Reference documentation](https://docs.liquibase.com/start/get-started/liquibase-sql.html)
and [Spring Boot integration](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/)

## Maven dependency

```xml

<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

## Spring Boot configuration

```yaml
spring:
    liquibase:
        enabled: true
        contexts: dev,prod
        change-log: classpath:/db/changelog/db.changelog-master.yaml
```

# Testcontainers

[Reference documentation](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)
and [Spring Boot integration](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)

## Maven dependencies

```xml

<dependencies>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>postgresql</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Configuration

```kotlin
class PostgresTestcontainersTestConfiguration {

    companion object {

        @ServiceConnection
        @Container
        @JvmStatic
        val postgresContainer = PostgreSQLContainer("postgres:16-alpine")
            .withDatabaseName("PLAYGROUND")
            .withUsername("playground-user")
            .withPassword("password")
    }
}
```

```kotlin
@ActiveProfiles("testcontainers-test")
@Import(SpringDataJpaConfiguration::class)
@ImportTestcontainers(value = [PostgresTestcontainersTestConfiguration::class])
annotation class TestWithPostgresTestcontainers
```

```kotlin
@DataJpaTest(showSql = true)
@TestWithPostgresTestcontainers
class PlaygroundEntityRepositoryTestcontainersTest {

    @Autowired
    private lateinit var playgroundEntityRepository: PlaygroundEntityRepository

    // tests
}
```

# Spring Data JPA

[Reference documentation](https://spring.io/projects/spring-data-jpa)

## Configuration

```kotlin
@Configuration
@EnableJpaRepositories(basePackages = ["io.positivinh.playground.spring.data.jpa.repository"])
class SpringDataJpaConfiguration
```

## Test configuration

```yaml
spring:
    test:
        database:
            replace: none
```

## Entities

```kotlin
@Entity
@Table(name = "PLAYGROUND_ENTITY")
@SequenceGenerator(name = "playgroundEntityPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_SEQ", allocationSize = 1)
class PlaygroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityPkGenerator")
    var id: Long? = null

    var data: String? = null

}
```

## Relationships

### Unidirectional

### Bidirectional

```kotlin
@Entity
@Table(name = "PLAYGROUND_ENTITY")
@SequenceGenerator(name = "playgroundEntityPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_SEQ", allocationSize = 1)
class PlaygroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityPkGenerator")
    var id: Long? = null

    var data: String? = null

    @JoinColumn(name = "PLAYGROUND_ENTITY_ID")
    @OneToMany(cascade = [CascadeType.ALL])
    var records: MutableSet<PlaygroundEntityRecord> = HashSet()

    fun addRecord(playgroundEntityRecord: PlaygroundEntityRecord): PlaygroundEntity {

        playgroundEntityRecord.playgroundEntity = this
        this.records.add(playgroundEntityRecord)
        return this
    }
}
```

```kotlin
@Entity
@Table(name = "PLAYGROUND_ENTITY_RECORD")
@SequenceGenerator(name = "playgroundEntityRecordPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_RECORD_SEQ", allocationSize = 1)
class PlaygroundEntityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityRecordPkGenerator")
    var id: Long? = null

    @JoinColumn(name = "PLAYGROUND_ENTITY_ID")
    @ManyToOne
    var playgroundEntity: PlaygroundEntity? = null

    @Column(name = "RECORD_DATA")
    var recordData: String? = null
}
```

# Auditing

[Reference documentation](https://docs.spring.io/spring-data/jpa/reference/auditing.html)

## Configuration

```kotlin
@Configuration
@EnableJpaRepositories(basePackages = ["io.positivinh.playground.spring.data.jpa.repository"])
@EnableJpaAuditing
class SpringDataJpaConfiguration {

    @Bean
    fun auditorProvider(): AuditorAware<String> {

        return object : AuditorAware<String> {
            override fun getCurrentAuditor(): Optional<String> {
                return Optional.of("vinh")
            }
        }
    }
}
```

## Auditable entity

```kotlin
@Entity
@Table(name = "PLAYGROUND_ENTITY")
@SequenceGenerator(name = "playgroundEntityPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_SEQ", allocationSize = 1)
@EntityListeners(AuditingEntityListener::class) // https://docs.spring.io/spring-data/jpa/reference/auditing.html#jpa.auditing.configuration
class PlaygroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityPkGenerator")
    var id: Long? = null

    var data: String? = null

    @JoinColumn(name = "PLAYGROUND_ENTITY_ID")
    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH], fetch = FetchType.LAZY)
    var records: MutableSet<PlaygroundEntityRecord> = HashSet()

    @CreatedDate
    @Column(name = "CREATION_TIME")
    var creationTime: LocalDateTime? = null

    @CreatedBy
    @Column(name = "CREATED_BY")
    var createdBy: String? = null

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    var updateTime: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    var updatedBy: String? = null
}
```
