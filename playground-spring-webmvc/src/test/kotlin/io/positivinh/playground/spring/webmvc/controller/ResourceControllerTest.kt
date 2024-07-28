package io.positivinh.playground.spring.webmvc.controller

import com.crabshue.commons.json.serialization.JsonSerializer
import io.positivinh.playground.spring.webmvc.dtos.PageResource
import io.positivinh.playground.spring.webmvc.dtos.Resource
import io.positivinh.playground.spring.webmvc.facade.ResourceFacade
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.anyOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @MockBean
    private lateinit var resourceFacade: ResourceFacade

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getResources() {

        val resourcePage = PageResource(listOf(Resource().name("playground"), Resource().name("other")))

        Mockito.doReturn(resourcePage).`when`(resourceFacade).getResources(Mockito.any(), Mockito.any())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/resources")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2))

    }

    @Test
    fun createResource() {

        val resource = Resource().name("playground")

        Mockito.doAnswer { invokation -> invokation.arguments[0] }.`when`(resourceFacade).createResource(anyOrNull())

        mockMvc.perform(
            MockMvcRequestBuilders.post("/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonSerializer.of(resource).serialize<String>())
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect { MockMvcResultMatchers.jsonPath("$.name").value("playground") }
    }
}
