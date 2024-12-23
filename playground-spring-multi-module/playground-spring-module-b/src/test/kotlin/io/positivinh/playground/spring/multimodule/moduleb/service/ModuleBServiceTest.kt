package io.positivinh.playground.spring.multimodule.moduleb.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ModuleBServiceTest {

    private val moduleBService: ModuleBService = ModuleBService()

    @Test
    fun returnSomething() {

        val res = moduleBService.returnSomething()
        Assertions.assertThat(res).isEqualTo("do something")
    }
}
