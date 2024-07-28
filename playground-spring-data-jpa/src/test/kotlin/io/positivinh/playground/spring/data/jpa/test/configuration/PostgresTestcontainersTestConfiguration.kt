package io.positivinh.playground.spring.data.jpa.test.configuration

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

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
