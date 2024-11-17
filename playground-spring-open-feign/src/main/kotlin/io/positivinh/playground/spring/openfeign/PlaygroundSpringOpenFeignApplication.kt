package io.positivinh.playground.spring.openfeign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PlaygroundSpringOpenFeignApplication

fun main(args: Array<String>) {

    runApplication<PlaygroundSpringOpenFeignApplication>(*args)
}
