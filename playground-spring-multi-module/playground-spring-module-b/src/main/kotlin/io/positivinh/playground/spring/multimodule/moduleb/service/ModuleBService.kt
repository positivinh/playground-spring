package io.positivinh.playground.spring.multimodule.moduleb.service

import org.springframework.stereotype.Service

@Service
class ModuleBService {

    fun returnSomething(): String {

        println("doSomething")
        return "do something"
    }
}
