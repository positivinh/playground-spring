package io.unforgivinh.playground.spring.webmvc.controller

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playground.spring.webmvc.controllers.PlaygroundApi
import io.unforgivinh.playground.spring.webmvc.dtos.PagePlayground
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaygroundController : PlaygroundApi {

    private val logger = getLogger()

    override fun getPlaygrounds(searchTerm: String?, pageable: Pageable?): ResponseEntity<PagePlayground> {

        logger.info("Received request [GET /playgrounds] with parameters [searchTerm=$searchTerm] and [pageable=$pageable]")

        val ret = PagePlayground(
            listOf(
                Playground().name("playground-spring"),
                Playground().name("test")
            )
        )
        return ResponseEntity.ok(ret)
    }

}
