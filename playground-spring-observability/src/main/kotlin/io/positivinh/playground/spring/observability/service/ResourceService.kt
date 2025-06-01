package io.positivinh.playground.spring.observability.service

import io.micrometer.core.annotation.Counted
import io.micrometer.core.annotation.Timed
import io.micrometer.core.aop.MeterTag
import io.micrometer.observation.annotation.Observed
import io.micrometer.tracing.annotation.NewSpan
import io.positivinh.playground.spring.observability.model.Resource
import org.springframework.stereotype.Service

@Service
class ResourceService {

    @Timed
    @Counted
    @NewSpan
    @Observed(lowCardinalityKeyValues = ["key1", "value1", "key3", "#{parameter}"])
    fun listResources(@MeterTag("key2") parameter: String? = "method parameter"): List<Resource> {

        return listOf(
            Resource("resource 1"),
            Resource("resource 2")
        )
    }
}
