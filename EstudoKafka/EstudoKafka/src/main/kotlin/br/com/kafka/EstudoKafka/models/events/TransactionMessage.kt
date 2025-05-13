package br.com.kafka.EstudoKafka.models.events

import br.com.kafka.EstudoKafka.models.enums.TransactionType

data class TransactionMessage(
    val transactionId: Long,
    val userId: Long,
    val transactionType: TransactionType,
    val amount: Double
)
