package io.positivinh.playground.spring.mybatis

import com.crabshue.commons.kotlin.logging.getLogger
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundSpringMyBatisApplication {

    val log = getLogger()

    @PostConstruct
    fun postConstruct() {

        log.info("reached post construct")
    }
}

fun main(args: Array<String>) {

    runApplication<PlaygroundSpringMyBatisApplication>(*args)
}

