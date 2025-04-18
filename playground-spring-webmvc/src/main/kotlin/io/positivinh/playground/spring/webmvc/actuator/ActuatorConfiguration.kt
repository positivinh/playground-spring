package io.positivinh.playground.spring.webmvc.actuator

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ActuatorConfiguration {

    /**
     * [HttpExchangeRepository][org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository] bean for actuator endpoint _/httpexchanges_
     */
    @Bean
    fun inMemoryHttpExchangeRepository(): InMemoryHttpExchangeRepository {

        return InMemoryHttpExchangeRepository()
            .apply {
                this.setCapacity(1000)
            }
    }
}
