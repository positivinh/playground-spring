package io.positivinh.playground.spring.webmvc.service.impl

import io.positivinh.playground.spring.webmvc.ResourceFixtureFactory
import io.positivinh.playground.spring.webmvc.repository.ResourceInMemoryRepository
import io.positivinh.playground.spring.webmvc.validator.ResourceValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ResourceServiceImplTest {

    @Spy
    private lateinit var resourceInMemoryRepository: ResourceInMemoryRepository

    @Mock
    private lateinit var resourceValidator: ResourceValidator

    @InjectMocks
    private lateinit var playgroundService: ResourceServiceImpl


    @Test
    fun listPlaygrounds() {

        val res = playgroundService.listResources()

        Assertions.assertFalse(res.isEmpty)
        Assertions.assertEquals(1, res.content.size)
    }

    @Test
    fun createPlayground() {

        val inMemoryListBeforeCreation = playgroundService.listResources()

        val playground = ResourceFixtureFactory.defaultFixture()

        val res = playgroundService.createResource(playground)

        Assertions.assertEquals(playground, res)

        val inMemoryListAfterCreation = playgroundService.listResources()

        Assertions.assertEquals(inMemoryListBeforeCreation.size + 1, inMemoryListAfterCreation.size)
    }

    @BeforeEach
    fun runDataMigration() {

        playgroundService.playgroundDataMigration()
    }
}
