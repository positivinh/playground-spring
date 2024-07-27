package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.configuration.SpringDataJpaConfiguration
import io.positivinh.playground.spring.data.jpa.test.configuration.PostgresTestcontainersTestConfiguration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.testcontainers.context.ImportTestcontainers
import org.springframework.context.annotation.Import

/**
 * Test integration with database
 */
@DataJpaTest(showSql = true)
@Import(SpringDataJpaConfiguration::class)
@ImportTestcontainers(value = [PostgresTestcontainersTestConfiguration::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaygroundEntityLiquibaseTest {

    @Autowired
    private lateinit var playgroundEntityRepository: PlaygroundEntityRepository

    @Test
    fun allContextMigrationsExecuted() {

        val findAllByCreatedBy = playgroundEntityRepository.findAllByCreatedBy("liquibase")

        Assertions.assertTrue(findAllByCreatedBy.isNotEmpty())
        Assertions.assertEquals(1, findAllByCreatedBy.size)
        Assertions.assertEquals(2, findAllByCreatedBy[0].records.size)
    }
}
