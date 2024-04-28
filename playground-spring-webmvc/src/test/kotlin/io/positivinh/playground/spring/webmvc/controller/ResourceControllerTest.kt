package io.positivinh.playground.spring.webmvc.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getPlaygrounds() {

        mockMvc.get("/playgrounds")
            .andExpect { MockMvcResultMatchers.status().isOk }
            .andExpect { MockMvcResultMatchers.jsonPath("$.content.length()").value(2) }
    }
}
