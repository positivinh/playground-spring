package io.unforgivinh.playground.spring.webmvc.service.impl

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import io.unforgivinh.playground.spring.webmvc.repository.PlaygroundInMemoryRepository
import io.unforgivinh.playground.spring.webmvc.service.PlaygroundService
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PlaygroundServiceImpl(
    private val playgroundInMemoryRepository: PlaygroundInMemoryRepository
) : PlaygroundService {

    private val logger = getLogger()

    @PostConstruct
    fun playgroundDataMigration() {

        playgroundInMemoryRepository.deleteAll()

        playgroundInMemoryRepository.save(
            Playground()
                .name("Default playground created by migration")
        )

        logger.info("Finished data migration for playgrounds. Initial list of playgrounds: ${playgroundInMemoryRepository.findAll()}")

    }

    override fun listPlaygrounds(searchTerm: String?, pageable: Pageable?): Page<Playground> {

        val ret: List<Playground> = playgroundInMemoryRepository.findAll().toList()

        return PageImpl(ret, pageable!!, ret.size.toLong())
    }

    override fun createPlayground(playground: Playground): Playground {

        logger.debug("Creating playground [$playground]")

        playgroundInMemoryRepository.save(playground)

        logger.info("Created playground [$playground]")

        return playground
    }
}
