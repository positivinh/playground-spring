package io.positivinh.playground.spring.mybatis

import io.positivinh.playground.spring.mybatis.test.OracleTestcontainersConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [OracleTestcontainersConfiguration::class])
class PlaygroundSpringMyBatisApplicationIT {

    @Test
    fun contextLoads() {
    }
}
