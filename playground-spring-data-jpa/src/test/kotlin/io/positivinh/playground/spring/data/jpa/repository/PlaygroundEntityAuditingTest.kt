package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.test.PlaygroundEntityFixtureFactory
import io.positivinh.playground.spring.data.jpa.test.configuration.TestWithPostgresTestcontainers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest(showSql = true)
@TestWithPostgresTestcontainers
class PlaygroundEntityAuditingTest {

    @Autowired
    private lateinit var playgroundEntityRepository: PlaygroundEntityRepository

    @Test
    fun auditing() {

        val entity = PlaygroundEntityFixtureFactory.newFixture()

        val saved = playgroundEntityRepository.saveAndFlush(entity)

        Assertions.assertNotNull(saved.creationTime)
        Assertions.assertNotNull(saved.updateTime)
        Assertions.assertEquals(saved.creationTime, saved.updateTime)
        Assertions.assertEquals("vinh", saved.createdBy)
        Assertions.assertEquals("vinh", saved.updatedBy)

        val retrieved = playgroundEntityRepository.findByIdOrNull(saved.id) ?: Assertions.fail()
        retrieved.data = "updated data"

        val updated = playgroundEntityRepository.saveAndFlush(retrieved)

        Assertions.assertNotNull(updated.creationTime)
        Assertions.assertNotNull(updated.updateTime)
        Assertions.assertNotEquals(updated.creationTime, updated.updateTime)
        Assertions.assertEquals("vinh", saved.createdBy)
        Assertions.assertEquals("vinh", saved.updatedBy)
    }
}
