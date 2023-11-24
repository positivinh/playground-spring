package io.unforgivinh.playground.spring.webmvc.facade

import io.unforgivinh.playground.spring.webmvc.controllers.ResourceApiDelegate
import io.unforgivinh.playground.spring.webmvc.dtos.PageResource
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import io.unforgivinh.playground.spring.webmvc.mapper.ResourceMapper
import io.unforgivinh.playground.spring.webmvc.service.ResourceService
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ResourceFacade(private val resourceService: ResourceService) : ResourceApiDelegate {

    private val resourceMapper = Mappers.getMapper(ResourceMapper::class.java)

    override fun createResource(resource: Resource): Resource {

        return resourceService.createResource(resource)
    }

    override fun getResources(searchTerm: String?, pageable: Pageable?): PageResource {

        val ret = resourceService.listResources(searchTerm, pageable ?: Pageable.unpaged())
        return resourceMapper.mapPageable(ret)
    }
}
