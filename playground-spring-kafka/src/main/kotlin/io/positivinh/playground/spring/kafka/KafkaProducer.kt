package io.positivinh.playground.spring.kafka

import com.crabshue.commons.kotlin.logging.getLogger
import io.positivinh.playground.spring.kafka.messages.KafkaMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, KafkaMessage>) {

    private val log = getLogger()

    fun sendMessage(topic: String = "defaultTopic", message: KafkaMessage) {
        kafkaTemplate.send(topic, message)
            .also { log.info("Message sent $it") }
    }
}
