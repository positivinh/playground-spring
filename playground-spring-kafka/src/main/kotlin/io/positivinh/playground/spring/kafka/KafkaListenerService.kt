package io.positivinh.playground.spring.kafka

import com.crabshue.commons.kotlin.logging.getLogger
import io.positivinh.playground.spring.kafka.messages.KafkaMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaListenerService {

    private val log = getLogger()

    @KafkaListener(topics = ["defaultTopic"])
    fun processMessage(message: KafkaMessage) {

        log.info("Processing message: $message")
    }
}
