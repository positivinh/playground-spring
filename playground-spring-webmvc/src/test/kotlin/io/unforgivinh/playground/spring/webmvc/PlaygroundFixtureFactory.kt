package io.unforgivinh.playground.spring.webmvc

import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

object PlaygroundFixtureFactory {

    fun fixture(name: String? = "default test fixture playground"): Playground {

        return Playground()
            .name(name)
    }

    fun defaultFixture(): Playground {

        return fixture()
    }


    fun defaultFixturePage(): Page<Playground> {

        val ret = listOf(defaultFixture())

        return PageImpl(ret)
    }
}
