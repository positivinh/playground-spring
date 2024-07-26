package io.positivinh.playground.spring.data.jpa.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["io.positivinh.playground.spring.data.jpa.repository"])
class SpringDataJpaConfiguration {
}
