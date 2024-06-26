package io.positivinh.playground.spring.webmvc.service

import io.positivinh.playground.spring.webmvc.dtos.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ResourceService {

    fun listResources(searchTerm: String? = null, pageable: Pageable? = Pageable.unpaged()): Page<Resource>

    fun createResource(resource: Resource): Resource
}
