package io.positivinh.playground.spring.httpinterface.client

import io.positivinh.playground.spring.httpinterface.httpstatus.client.HttpStatusApi
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@Configuration
class HttpStatusApiClientConfiguration(val httpStatusApiClientConfigurationProperties: HttpStatusApiClientConfigurationProperties) {

    @Bean
    fun httpServiceProxyFactory(): HttpServiceProxyFactory {

        val webClient = WebClient.builder()
            .baseUrl(httpStatusApiClientConfigurationProperties.url)
            .build()

        return HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient))
            .build()
    }

    @ConditionalOnBean(HttpServiceProxyFactory::class)
    @Bean
    fun httpStatusApiClient(httpServiceProxyFactory: HttpServiceProxyFactory): HttpStatusApi {

        return httpServiceProxyFactory.createClient(HttpStatusApi::class.java)
    }
}
