# Playground Spring Data JPA

## Technologies

- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Testcontainers](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)
  and [Spring Boot integration](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
- [Spring Boot Docker compose support](https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose)
- [Liquibase](https://docs.liquibase.com/start/get-started/liquibase-sql.html)
  and [Spring Boot integration](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/)

## Docker compose support

### Maven dependency

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Spring Boot configuration

```yaml
spring:
    docker:
        compose:
            enabled: true
            skip:
                in-tests: false
            file: docker-compose.yaml
```

### Docker compose file

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

## Liquibase

### Maven dependency

```xml

<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

### Spring Boot configuration

```yaml
spring:
    liquibase:
        enabled: true
        contexts: dev,prod
        change-log: classpath:/db/changelog/db.changelog-master.yaml
```

## Spring Data JPA

### Configuration

```kotlin
@Configuration
@EnableJpaRepositories(basePackages = ["io.positivinh.playground.spring.data.jpa.repository"])
class SpringDataJpaConfiguration
```

### Test configuration
```yaml
spring:
    test:
        database:
            replace: none
```

### Entities

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

### Relationships


