package br.com.kafka.EstudoKafka.service

import br.com.kafka.EstudoKafka.models.events.TransactionMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumerService {

    private val logger = LoggerFactory.getLogger(KafkaConsumerService::class.java)

    @KafkaListener(topics = ["transaction-topic"], groupId = "reward-group")
    fun consume(message : TransactionMessage) {
        try {
            logger.info("Transaction is ready for rewards $message")
        } catch (e : Exception) {
            logger.error("Error consuming message ${e.message}", e)
        }
    }

}