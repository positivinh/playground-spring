package io.positivinh.playground.spring.httpinterface.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "http-status.client")
data class HttpStatusApiClientConfigurationProperties(var url: String = "")


