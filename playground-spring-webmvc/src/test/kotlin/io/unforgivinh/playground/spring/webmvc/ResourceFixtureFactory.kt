package io.unforgivinh.playground.spring.webmvc

import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

object ResourceFixtureFactory {

    fun fixture(name: String? = "default test fixture playground"): Resource {

        return Resource()
            .name(name)
    }

    fun defaultFixture(): Resource {

        return fixture()
    }


    fun defaultFixturePage(): Page<Resource> {

        val ret = listOf(defaultFixture())

        return PageImpl(ret)
    }
}
