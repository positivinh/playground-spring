package io.positivinh.playground.spring.autoconfigure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "playground.bean")
data class PlaygroundAutoConfigurationProperties(val property: String = "default")
