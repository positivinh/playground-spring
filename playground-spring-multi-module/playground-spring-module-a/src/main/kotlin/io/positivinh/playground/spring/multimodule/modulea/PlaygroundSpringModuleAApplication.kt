package io.positivinh.playground.spring.multimodule.modulea

import io.positivinh.playground.spring.multimodule.moduleb.PlaygroundSpringModuleBConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(PlaygroundSpringModuleBConfiguration::class)
class PlaygroundSpringModuleAApplication {
}

fun main(args: Array<String>) {

    runApplication<PlaygroundSpringModuleAApplication>(*args)
}
