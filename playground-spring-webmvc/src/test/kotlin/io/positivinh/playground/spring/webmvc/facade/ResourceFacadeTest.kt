package io.positivinh.playground.spring.webmvc.facade

import io.positivinh.playground.spring.webmvc.dtos.Resource
import io.positivinh.playground.spring.webmvc.service.ResourceService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.anyOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class ResourceFacadeTest {

    @MockitoBean
    private lateinit var resourceService: ResourceService

    @Autowired
    private lateinit var resourceFacade: ResourceFacade

    @WithMockUser(authorities = ["ROLE_ADMIN"])
    @Test
    fun createResource() {

        Mockito.doAnswer { invokation -> invokation.arguments[0] }
            .`when`(resourceService).createResource(anyOrNull())

        Assertions.assertDoesNotThrow { resourceFacade.createResource(Resource().apply { this.name("playground") }) }
    }

    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun createResource_forbidden() {

        Mockito.doAnswer { invokation -> invokation.arguments[0] }
            .`when`(resourceService).createResource(anyOrNull())

        Assertions.assertThrows(AccessDeniedException::class.java) { resourceFacade.createResource(Resource().apply { this.name("playground") }) }
    }
}
