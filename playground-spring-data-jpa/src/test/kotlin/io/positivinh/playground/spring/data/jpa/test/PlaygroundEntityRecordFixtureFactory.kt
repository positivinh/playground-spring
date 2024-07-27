package io.positivinh.playground.spring.data.jpa.test

import io.positivinh.playground.spring.data.jpa.entity.PlaygroundEntity
import io.positivinh.playground.spring.data.jpa.entity.PlaygroundEntityRecord

object PlaygroundEntityRecordFixtureFactory {

    fun newFixture(
        id: Long? = null,
        playgroundEntity: PlaygroundEntity? = PlaygroundEntityFixtureFactory.newFixture(),
        recordData: String? = PlaygroundEntityTestConstants.RECORD_ENTITY_DATA
    ): PlaygroundEntityRecord {

        return PlaygroundEntityRecord()
            .apply {
                this.id = id
                playgroundEntity?.addRecord(this)
                this.recordData = recordData
            }
    }

}
