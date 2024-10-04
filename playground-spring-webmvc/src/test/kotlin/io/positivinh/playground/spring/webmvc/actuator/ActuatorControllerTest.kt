package io.positivinh.playground.spring.webmvc.actuator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
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

    @Test
    fun infoActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/info")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.build.artifact").value("playground-spring-webmvc"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.build.version").isNotEmpty)
    }

    @Test
    fun beansActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/beans")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.contexts.application.beans").isNotEmpty)
    }

    @Test
    fun conditionsActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/conditions")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.contexts.application.positiveMatches").isNotEmpty)
    }

    @Test
    fun environmentActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/env")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.activeProfiles").isEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.defaultProfiles").value("default"))
    }

    @Test
    fun loggersActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/loggers")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.levels").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.loggers.ROOT.effectiveLevel").value("INFO"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.loggers.['io.positivinh.playground.spring.webmvc'].effectiveLevel").value("INFO"))
    }

    @Test
    fun scheduledTasksActuatorEndpoint() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/actuator/scheduledtasks")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.cron").isEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.fixedDelay").isEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.fixedRate").isEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.custom").isEmpty)
    }

}
