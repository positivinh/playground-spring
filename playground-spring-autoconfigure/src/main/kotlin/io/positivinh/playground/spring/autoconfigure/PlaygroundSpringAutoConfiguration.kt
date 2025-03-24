package io.positivinh.playground.spring.autoconfigure

import io.positivinh.playground.spring.autoconfigure.domain.PlaygroundBean
import io.positivinh.playground.spring.autoconfigure.properties.PlaygroundAutoConfigurationProperties
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource

/**
 * See [Reference documentation](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html#features.developing-auto-configuration.custom-starter)
 */
@AutoConfiguration
@ConfigurationPropertiesScan
@ConditionalOnProperty(prefix = "playground.autoconfiguration", value = ["enabled"], havingValue = "true", matchIfMissing = true)
@PropertySource("classpath:/playground-autoconfigure.properties")
class PlaygroundSpringAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun playgroundBean(playgroundAutoConfigurationProperties: PlaygroundAutoConfigurationProperties): PlaygroundBean {

        return PlaygroundBean("autoconfigured", property = playgroundAutoConfigurationProperties.property)
    }
}
