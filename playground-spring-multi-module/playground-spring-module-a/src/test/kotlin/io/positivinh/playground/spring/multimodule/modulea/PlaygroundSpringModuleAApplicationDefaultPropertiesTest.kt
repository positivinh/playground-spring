package io.positivinh.playground.spring.multimodule.modulea

import io.positivinh.playground.spring.autoconfigure.domain.PlaygroundBean
import io.positivinh.playground.spring.multimodule.moduleb.service.ModuleBService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class PlaygroundSpringModuleAApplicationDefaultPropertiesTest {

    @Autowired
    private lateinit var applicationContext: ApplicationContext


    @Test
    fun contextLoadsBeansFromCustomLibrary() {

        val libraryService: ModuleBService = applicationContext.getBean(ModuleBService::class.java)

        Assertions.assertNotNull(libraryService)
        Assertions.assertEquals("do something", libraryService.returnSomething())
    }

    @Test
    fun contextLoadsAutoConfigurationByTransitivity() {

        // load autoconfiguration in dependencies
        val autoconfiguredBean = applicationContext.getBean(PlaygroundBean::class.java)

        Assertions.assertNotNull(autoconfiguredBean)
        Assertions.assertEquals("autoconfigured", autoconfiguredBean.name)
        Assertions.assertEquals("from autoconfiguration properties", autoconfiguredBean.property)
    }
}
