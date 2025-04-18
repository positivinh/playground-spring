package io.positivinh.playground.spring.observability

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundSpringObservabilityApplication

fun main(args: Array<String>) {
    runApplication<PlaygroundSpringObservabilityApplication>(*args)
}
