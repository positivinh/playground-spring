package io.positivinh.playground.spring.webmvc.actuator

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.stereotype.Component
import java.util.*

/**
 * Customer info endpoint contributor.
 *
 * The name is what precedes the suffix _InfoContributor_.
 *
 * See [Reference documentation](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#actuator.endpoints.info.writing-custom-info-contributors)
 */
@Component
class CustomInfoContributor : InfoContributor {

    override fun contribute(builder: Info.Builder?) {
        builder?.withDetail("example", Collections.singletonMap("key", "value"))
    }
}
