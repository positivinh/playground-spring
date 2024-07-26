package io.positivinh.playground.spring.data.jpa.test

import io.positivinh.playground.spring.data.jpa.entity.PlaygroundEntity

object PlaygroundEntityFixtureFactory {

    fun newFixture(
        id: Long? = null,
        data: String? = PlaygroundEntityTestConstants.ENTITY_DATA
    ): PlaygroundEntity {

        return PlaygroundEntity()
            .apply {
                this.id = id
                this.data = data
            }
    }

}
