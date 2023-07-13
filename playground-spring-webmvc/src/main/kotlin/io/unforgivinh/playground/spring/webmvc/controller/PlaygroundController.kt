package io.unforgivinh.playground.spring.webmvc.controller

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playground.spring.webmvc.controllers.PlaygroundApi
import io.unforgivinh.playground.spring.webmvc.dtos.PagePlayground
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import io.unforgivinh.playground.spring.webmvc.facade.PlaygroundFacade
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaygroundController(var playgroundFacade: PlaygroundFacade) : PlaygroundApi {

    private val logger = getLogger()

    override fun getPlaygrounds(searchTerm: String?, pageable: Pageable?): ResponseEntity<PagePlayground> {

        logger.info("Received request [GET /playgrounds] with parameters [searchTerm=$searchTerm] and [pageable=$pageable]")

        val ret: PagePlayground = playgroundFacade.getPlaygrounds(searchTerm, pageable)
        return ResponseEntity.ok(ret)
    }

    override fun createPlayground(playground: Playground): ResponseEntity<Playground> {

        val ret: Playground = playgroundFacade.createPlayground(playground)

        return ResponseEntity.status(HttpStatus.CREATED).body(ret)
    }
}
