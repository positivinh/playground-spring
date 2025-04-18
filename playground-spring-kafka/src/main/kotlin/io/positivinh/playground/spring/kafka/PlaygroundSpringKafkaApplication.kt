package io.positivinh.playground.spring.kafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class PlaygroundSpringKafkaApplication {
}

fun main(args: Array<String>) {
    runApplication<PlaygroundSpringKafkaApplication>(*args)
}
