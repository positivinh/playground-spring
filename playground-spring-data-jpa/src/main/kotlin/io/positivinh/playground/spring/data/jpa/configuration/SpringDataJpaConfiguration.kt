package io.positivinh.playground.spring.data.jpa.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*


@Configuration
@EnableJpaRepositories(basePackages = ["io.positivinh.playground.spring.data.jpa.repository"])
@EnableJpaAuditing
class SpringDataJpaConfiguration {

    @Bean
    fun auditorProvider(): AuditorAware<String> {

        return AuditorAware<String> { Optional.of("vinh") }
    }
}
