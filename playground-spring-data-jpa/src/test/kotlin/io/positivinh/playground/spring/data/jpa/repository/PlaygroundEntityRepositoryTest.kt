package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.test.PlaygroundEntityFixtureFactory
import io.positivinh.playground.spring.data.jpa.test.PlaygroundEntityTestConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@DataJpaTest(showSql = true)
@ActiveProfiles("test")
class PlaygroundEntityRepositoryTest {

    @Autowired
    private lateinit var playgroundEntityRepository: PlaygroundEntityRepository

    @Test
    fun crud() {

        val sizeBeforeSave = playgroundEntityRepository.count()

        val entity = PlaygroundEntityFixtureFactory.newFixture(data = PlaygroundEntityTestConstants.ENTITY_DATA)

        val saved = playgroundEntityRepository.save(entity)

        Assertions.assertNotNull(saved)
        Assertions.assertNotNull(saved.id)
        Assertions.assertEquals(PlaygroundEntityTestConstants.ENTITY_DATA, saved.data)

        val sizeAfterSave = playgroundEntityRepository.count()

        Assertions.assertEquals(sizeBeforeSave + 1, sizeAfterSave)

        val retrieved = playgroundEntityRepository.findByIdOrNull(saved.id)

        Assertions.assertNotNull(retrieved)
        Assertions.assertEquals(saved, retrieved)

        val entityUpdated = PlaygroundEntityFixtureFactory.newFixture(id = retrieved?.id, data = PlaygroundEntityTestConstants.ENTITY_DATA_2)

        val updated = playgroundEntityRepository.save(entityUpdated)

        val sizeAfterUpdate = playgroundEntityRepository.count()

        Assertions.assertEquals(sizeAfterSave, sizeAfterUpdate)

        val retrievedUpdate = playgroundEntityRepository.findByIdOrNull(saved.id)

        Assertions.assertEquals(updated.id, retrievedUpdate?.id)
        Assertions.assertEquals(PlaygroundEntityTestConstants.ENTITY_DATA_2, retrievedUpdate?.data)

        playgroundEntityRepository.deleteById(saved.id!!)

        val sizeAfterDelete = playgroundEntityRepository.count()

        Assertions.assertEquals(sizeBeforeSave, sizeAfterDelete)
    }
}
