package io.unforgivinh.playground.spring.webmvc.mapper

import io.unforgivinh.playground.spring.webmvc.dtos.PageResource
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import org.mapstruct.Mapper
import org.springframework.data.domain.Page

@Mapper
interface ResourceMapper {

    fun mapPageable(playgroundPage: Page<Resource>): PageResource
}
