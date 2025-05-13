package br.com.kafka.EstudoKafka.config

import br.com.kafka.EstudoKafka.models.events.TransactionMessage
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value("\${funds_app.kafka.servers_config}")
    lateinit var kafkaBootstrapServer : String

    @Bean
    fun consumerFactory() : ConsumerFactory<Long, TransactionMessage> {
        val configProps : MutableMap<String, Any> = HashMap()

        configProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaBootstrapServer
        configProps[ConsumerConfig.GROUP_ID_CONFIG] = "reward-group"

        configProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = ErrorHandlingDeserializer::class.java
        configProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ErrorHandlingDeserializer::class.java

        configProps[ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS] = LongDeserializer::class.java
        configProps[ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS] = JsonDeserializer::class.java

        configProps[JsonDeserializer.USE_TYPE_INFO_HEADERS] = false
        configProps[JsonDeserializer.VALUE_DEFAULT_TYPE] = "br.com.kafka.EstudoKafka.models.events.TransactionMessage"

        return DefaultKafkaConsumerFactory(configProps)
    }

    // KafkaMessageListenerContainer
    //ConcurrentMessageListenerContainer

    // MINUTO 14:51

}