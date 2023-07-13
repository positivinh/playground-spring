package io.unforgivinh.playground.spring.webmvc.service

import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlaygroundService {

    fun listPlaygrounds(searchTerm: String? = null, pageable: Pageable? = Pageable.unpaged()): Page<Playground>

    fun createPlayground(playground: Playground): Playground
}
