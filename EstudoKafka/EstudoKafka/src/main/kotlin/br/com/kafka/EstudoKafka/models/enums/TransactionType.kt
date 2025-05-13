package br.com.kafka.EstudoKafka.models.enums

enum class TransactionType(val type: String){
    DEPOSIT("DEPOSIT"),
    TRANSFER("TRANSFER"),
    WITHDRAW("WITHDRAW"),
    SEND("SEND")
}