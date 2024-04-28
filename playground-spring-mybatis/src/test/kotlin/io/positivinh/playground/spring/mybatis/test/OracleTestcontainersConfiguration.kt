package io.positivinh.playground.spring.mybatis.test

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.OracleContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Configuration
@Testcontainers
class OracleTestcontainersConfiguration {

    companion object {

        @Container
        @ServiceConnection
        private var oracleContainer: OracleContainer = OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
            .withDatabaseName("testDB")
            .withUsername("testUser")
            .withPassword("testPassword")
    }

}
