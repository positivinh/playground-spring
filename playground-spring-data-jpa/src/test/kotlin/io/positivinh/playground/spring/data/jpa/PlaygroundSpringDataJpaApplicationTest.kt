package io.positivinh.playground.spring.data.jpa

import io.positivinh.playground.spring.data.jpa.test.configuration.PostgresTestcontainersTestConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.context.ImportTestcontainers

@SpringBootTest
@ImportTestcontainers(value = [PostgresTestcontainersTestConfiguration::class])
class PlaygroundSpringDataJpaApplicationTest {

    @Test
    fun contextLoads() {

    }


}

