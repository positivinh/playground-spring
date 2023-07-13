package io.unforgivinh.playground.spring.webmvc.mapper

import io.unforgivinh.playground.spring.webmvc.PlaygroundFixtureFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class PlaygroundMapperTest {

    private val playgroundMapper = Mappers.getMapper(PlaygroundMapper::class.java)

    @Test
    fun mapPageable() {

        val page = PlaygroundFixtureFactory.defaultFixturePage()

        val res = playgroundMapper.mapPageable(page)

        Assertions.assertFalse(res.empty)
    }
}
