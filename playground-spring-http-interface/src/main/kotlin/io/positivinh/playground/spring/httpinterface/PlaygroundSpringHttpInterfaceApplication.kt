package io.positivinh.playground.spring.httpinterface

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class PlaygroundSpringHttpInterfaceApplication {
}

fun main(args: Array<String>) {

    runApplication<PlaygroundSpringHttpInterfaceApplication>(*args)
}
