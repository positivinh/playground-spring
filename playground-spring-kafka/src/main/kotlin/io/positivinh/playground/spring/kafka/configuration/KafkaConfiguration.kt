package io.positivinh.playground.spring.kafka.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfiguration {
    @Bean
    fun topic() = NewTopic("defaultTopic", 10, 1)

}
