package io.unforgivinh.playground.spring.webmvc.mapper

import io.unforgivinh.playground.spring.webmvc.ResourceFixtureFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class ResourceMapperTest {

    private val resourceMapper = Mappers.getMapper(ResourceMapper::class.java)

    @Test
    fun mapPageable() {

        val page = ResourceFixtureFactory.defaultFixturePage()

        val res = resourceMapper.mapPageable(page)

        Assertions.assertFalse(res.empty)
    }
}
