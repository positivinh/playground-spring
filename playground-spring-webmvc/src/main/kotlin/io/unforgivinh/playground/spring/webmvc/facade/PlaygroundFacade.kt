package io.unforgivinh.playground.spring.webmvc.facade

import io.unforgivinh.playground.spring.webmvc.dtos.PagePlayground
import io.unforgivinh.playground.spring.webmvc.mapper.PlaygroundMapper
import io.unforgivinh.playground.spring.webmvc.service.PlaygroundService
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class PlaygroundFacade(private val playgroundService: PlaygroundService) {

    private val playgroundMapper = Mappers.getMapper(PlaygroundMapper::class.java)

    fun getPlaygrounds(searchTerm: String?, pageable: Pageable? = Pageable.unpaged()): PagePlayground {

        val ret = playgroundService.listPlaygrounds(searchTerm, pageable)
        return playgroundMapper.mapPageable(ret)
    }
}
