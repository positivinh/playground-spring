package io.positivinh.playground.spring.data.jpa

import io.positivinh.playground.spring.data.jpa.test.configuration.TestWithPostgresTestcontainers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestWithPostgresTestcontainers
class PlaygroundSpringDataJpaApplicationTest {

    @Test
    fun contextLoads() {

    }


}

