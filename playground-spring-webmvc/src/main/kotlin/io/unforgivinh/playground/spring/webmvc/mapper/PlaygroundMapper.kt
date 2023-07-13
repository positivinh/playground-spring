package io.unforgivinh.playground.spring.webmvc.mapper

import io.unforgivinh.playground.spring.webmvc.dtos.PagePlayground
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import org.mapstruct.Mapper
import org.springframework.data.domain.Page

@Mapper
interface PlaygroundMapper {

    fun mapPageable(playgroundPage: Page<Playground>): PagePlayground
}
