package io.unforgivinh.playground.spring.webmvc.service.impl

import io.unforgivinh.playground.spring.webmvc.PlaygroundFixtureFactory
import io.unforgivinh.playground.spring.webmvc.repository.PlaygroundInMemoryRepository
import io.unforgivinh.playground.spring.webmvc.validator.PlaygroundValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PlaygroundServiceImplTest {

    @Spy
    private lateinit var playgroundInMemoryRepository: PlaygroundInMemoryRepository

    @Mock
    private lateinit var playgroundValidator: PlaygroundValidator

    @InjectMocks
    private lateinit var playgroundService: PlaygroundServiceImpl


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
