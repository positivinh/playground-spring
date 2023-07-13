package io.unforgivinh.playground.spring.webmvc.service.impl

import io.unforgivinh.playground.spring.webmvc.PlaygroundFixtureFactory
import io.unforgivinh.playground.spring.webmvc.repository.PlaygroundInMemoryRepository
import io.unforgivinh.playground.spring.webmvc.service.impl.PlaygroundServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlaygroundServiceImplTest {


    private val playgroundInMemoryRepository = PlaygroundInMemoryRepository()

    private val playgroundService: PlaygroundServiceImpl = PlaygroundServiceImpl(playgroundInMemoryRepository)


    @Test
    fun listPlaygrounds() {

        val res = playgroundService.listPlaygrounds()

        Assertions.assertFalse(res.isEmpty)
        Assertions.assertEquals(1, res.content.size)
    }

    @Test
    fun createPlayground() {

        val inMemoryListBeforeCreation = playgroundService.listPlaygrounds()

        val playground = PlaygroundFixtureFactory.defaultFixture()

        val res = playgroundService.createPlayground(playground)

        Assertions.assertEquals(playground, res)

        val inMemoryListAfterCreation = playgroundService.listPlaygrounds()

        Assertions.assertEquals(inMemoryListBeforeCreation.size + 1, inMemoryListAfterCreation.size)
    }

    @BeforeEach
    fun runDataMigration() {

        playgroundService.playgroundDataMigration()
    }
}
