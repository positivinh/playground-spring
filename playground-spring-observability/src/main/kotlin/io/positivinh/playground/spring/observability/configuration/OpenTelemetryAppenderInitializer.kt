//package io.positivinh.playground.spring.observability.configuration
//
//import io.opentelemetry.api.OpenTelemetry
//import org.springframework.beans.factory.InitializingBean
//import org.springframework.stereotype.Component
//
//@Component
//class OpenTelemetryAppenderInitializer(private val openTelemetry: OpenTelemetry) : InitializingBean {
//
//    override fun afterPropertiesSet() {
//        OpenTelemetryAppender.install(this.openTelemetry)
//    }
//}
