package io.positivinh.playground.spring.data.jpa.test.configuration

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@TestConfiguration
@Testcontainers
class PostgresTestcontainersTestConfiguration {

    companion object {

        @ServiceConnection
        @Container
        val postgresContainer = PostgreSQLContainer("postgres:16-alpine")
            .withDatabaseName("PLAYGROUND")
            .withUsername("playground-user")
            .withPassword("password")
    }
}
