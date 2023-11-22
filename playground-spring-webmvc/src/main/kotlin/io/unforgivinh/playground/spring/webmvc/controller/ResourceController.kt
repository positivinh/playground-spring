package io.unforgivinh.playground.spring.webmvc.controller

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playground.spring.webmvc.controllers.ResourceApi
import io.unforgivinh.playground.spring.webmvc.dtos.PageResource
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import io.unforgivinh.playground.spring.webmvc.facade.ResourceFacade
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController(var resourceFacade: ResourceFacade) : ResourceApi {

    private val logger = getLogger()

    override fun getResources(searchTerm: String?, pageable: Pageable?): ResponseEntity<PageResource> {

        logger.info("Received request [GET /playgrounds] with parameters [searchTerm=$searchTerm] and [pageable=$pageable]")

        val ret: PageResource = resourceFacade.getPlaygrounds(searchTerm, pageable)
        return ResponseEntity.ok(ret)
    }

    override fun createResource(playground: Resource): ResponseEntity<Resource> {

        val ret: Resource = resourceFacade.createPlayground(playground)

        return ResponseEntity.status(HttpStatus.CREATED).body(ret)
    }
}
