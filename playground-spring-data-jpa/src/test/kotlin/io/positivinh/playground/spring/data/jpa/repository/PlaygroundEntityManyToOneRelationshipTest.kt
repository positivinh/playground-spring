package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.test.PlaygroundEntityRecordFixtureFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@DataJpaTest(showSql = true)
@ActiveProfiles("docker-compose-test")
class PlaygroundEntityManyToOneRelationshipTest {

    @Autowired
    private lateinit var playgroundEntityRepository: PlaygroundEntityRepository

    @Autowired
    private lateinit var playgroundEntityRecordRepository: PlaygroundEntityRecordRepository

    @Test
    fun relationships() {

        // create entity record and associate with default entity
        val record = PlaygroundEntityRecordFixtureFactory.newFixture()

        val entity = record.playgroundEntity

        // create 2nd entity record and associate with default entity
        val record2 = PlaygroundEntityRecordFixtureFactory.newFixture(playgroundEntity = entity)

        Assertions.assertNotNull(entity)
        Assertions.assertTrue(entity!!.records.isNotEmpty())

        // save entity, which will automatically save associated records
        val saved = playgroundEntityRepository.save(entity)

        val retrieved = playgroundEntityRepository.findByIdOrNull(saved.id) ?: Assertions.fail()

        Assertions.assertEquals(2, retrieved.records.size)

        val recordId = retrieved.records.iterator().next().id
        Assertions.assertNotNull(recordId)

        val retrievedRecord = playgroundEntityRecordRepository.findByIdOrNull(recordId) ?: Assertions.fail()

        // retrieve entity via record
        Assertions.assertEquals(entity, retrievedRecord.playgroundEntity)
    }
}
