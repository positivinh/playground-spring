package io.positivinh.playground.spring.mybatis.test

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.OracleContainer
import org.testcontainers.junit.jupiter.Testcontainers


/**
 * Configuration for mutualized Oracle testcontainers. Slight deviation on reference documentation, as we do not use the annotation [org.testcontainers.containers.Container]
 *
 * see [Testcontainers documentation](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)
 *
 * details on the [image used](https://hub.docker.com/r/gvenzl/oracle-xe)
 */
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
