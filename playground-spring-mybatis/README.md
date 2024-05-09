# playground-spring-mybatis

- [ ] MyBatis Spring integration
- [ ] Usage of Testcontainers for testing purposes

## spring-mybatis

[Reference documentation](https://mybatis.org/spring/)

### Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>3.0.3</version>
    </dependency>
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc11</artifactId>
    </dependency>
</dependencies>
```

### Configuration

```kotlin
@Configuration
@MapperScan(basePackages = ["io.positivinh.playground.spring.mybatis.mappers"], sqlSessionFactoryRef="sqlSessionFactory")
class MybatisConfiguration {

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {

        val factoryBean = SqlSessionFactoryBean()
        factoryBean.setDataSource(dataSource)
        return factoryBean.getObject()
    }
}
```

### MyBatis mappers

```kotlin
/**
 * MyBatis entity mapper for [ResourceEntity].
 */
interface ResourceEntityMapper {

    /**
     * Retrieve a [ResourceEntity] by its id.
     */
    @Select("SELECT * FROM SCHEMA_USER.RESOURCES WHERE id = #{id}")
    @ResultMap("resourceMapping")
    fun getResourceEntityById(@Param("id") id: Long?): ResourceEntity

    /**
     * Dummy query used to declared mutualized mapping _resourceMapping_ for [ResourceEntity].
     */
    @Select("SELECT * FROM DUAL")
    @Results(
        id = "resourceMapping",
        value = [
            Result(column = "ID", property = "id", id = true, javaType = Long::class),
            Result(column = "COL_1", property = "property")
        ]
    )
    fun defaultMapping(): ResourceEntity
}
```


## Testcontainers integration with Spring Boot test

[Reference documentation](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)

### Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc11</artifactId>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>oracle-xe</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-testcontainers</artifactId>
    </dependency>
</dependencies>
```
### Configuration

```kotlin
@TestConfiguration
@Testcontainers
class OracleTestcontainersConfiguration {

    @Bean
    @ServiceConnection
    fun oracleContainer(): OracleContainer {

        return OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
            .withDatabaseName("testDB")
            .withUsername("schema_user")
            .withPassword("testPassword")
            .withInitScript("init-db.sql")
    }
}
```

### Usage

```kotlin
import org.junit.jupiter.api.Test

@SpringBootTest
@Import(OracleTestcontainersConfiguration::class)
class ResourceEntityMapperTest {

    @Autowired
    private lateinit var resourceEntityMapper: ResourceEntityMapper


    @Test
    fun testCrud() {
        // write tests

    }
}
```
