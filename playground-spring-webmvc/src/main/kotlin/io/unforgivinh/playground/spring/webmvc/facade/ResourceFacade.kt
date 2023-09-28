package io.unforgivinh.playground.spring.webmvc.facade

import io.unforgivinh.playground.spring.webmvc.dtos.PageResource
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import io.unforgivinh.playground.spring.webmvc.mapper.ResourceMapper
import io.unforgivinh.playground.spring.webmvc.service.ResourceService
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ResourceFacade(private val resourceService: ResourceService) {

    private val resourceMapper = Mappers.getMapper(ResourceMapper::class.java)

    fun getPlaygrounds(searchTerm: String?, pageable: Pageable? = Pageable.unpaged()): PageResource {

        val ret = resourceService.listResources(searchTerm, pageable)
        return resourceMapper.mapPageable(ret)
    }

    fun createPlayground(resource: Resource): Resource {

        return resourceService.createResource(resource)
    }
}
