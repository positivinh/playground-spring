package io.positivinh.playground.spring.multimodule.moduleb

import io.positivinh.playground.spring.autoconfigure.domain.PlaygroundBean
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class PlaygroundSpringModuleBConfigurationTest {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Test
    fun contextLoads() {

        // load autoconfiguration in dependencies
        val bean = applicationContext.getBean(PlaygroundBean::class.java)

        Assertions.assertNotNull(bean)
        Assertions.assertEquals("autoconfigured", bean.name)
        Assertions.assertEquals("from properties", bean.property)
    }
}
