package io.positivinh.playground.spring.kafka

import com.crabshue.commons.kotlin.logging.getLogger
import io.positivinh.playground.spring.kafka.messages.KafkaMessage
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class KafkaScenario(private val kafkaProducer: KafkaProducer) {

    private val log = getLogger()

    @EventListener(ApplicationReadyEvent::class)
    fun runKafkaScenario() {

        log.info("Ready")

        kafkaProducer.sendMessage(message = KafkaMessage("Hello"))
            .also { log.info("Message sent $it") }

    }
}
