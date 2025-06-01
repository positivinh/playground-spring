package io.positivinh.playground.spring.observability.controller

import io.positivinh.playground.spring.observability.model.Resource
import io.positivinh.playground.spring.observability.service.ResourceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playground/resources")
class ResourceController(val resourceService: ResourceService) {

    @GetMapping
    fun getResources(): List<Resource> = resourceService.listResources("something to trace")
}
