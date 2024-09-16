package io.positivinh.playground.spring.webmvc.actuator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun actuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$._links.health").exists())
    }

    @Test
    fun healthActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/health")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"))


    }
}
