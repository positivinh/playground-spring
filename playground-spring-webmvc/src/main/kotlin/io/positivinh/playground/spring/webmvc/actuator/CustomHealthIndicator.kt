package io.positivinh.playground.spring.webmvc.actuator

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.actuate.health.Status
import org.springframework.stereotype.Component

/**
 * Custom health indicator to express other health components.
 *
 * The name of the health indicator is what is before the suffix _HealthIndicator_.
 *
 * See [Reference documentation](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#actuator.endpoints.health.writing-custom-health-indicators)
 */
@Component
class CustomHealthIndicator: HealthIndicator {

    override fun health(): Health? {

        // health indicator business logic here

        return Health.status(Status.UNKNOWN).build()
    }
}
