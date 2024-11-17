package io.positivinh.playground.spring.httpinterface.client

import io.positivinh.playground.spring.httpinterface.httpstatus.client.HttpStatusApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@Configuration
class HttpStatusApiClientConfiguration(val httpStatusApiClientConfigurationProperties: HttpStatusApiClientConfigurationProperties) {


    @Bean
    fun httpStatusApiClient(): HttpStatusApi {

        val webClient = WebClient.builder()
            .baseUrl(httpStatusApiClientConfigurationProperties.url)
            .build()

        val httpServiceProxyFactory = HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient))
            .build()

        return httpServiceProxyFactory.createClient(HttpStatusApi::class.java)
    }
}
