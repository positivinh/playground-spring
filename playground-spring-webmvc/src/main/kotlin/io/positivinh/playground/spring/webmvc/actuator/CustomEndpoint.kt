package io.positivinh.playground.spring.webmvc.actuator

import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component

@Component
@Endpoint(id = "custom", enableByDefault = true)
class CustomEndpoint {

    @ReadOperation
    fun getCustomData(): String {

        return "custom data"
    }
}
