package com.example.mylibrary.config

import com.example.mylibrary.events.RecomendacaoEvent
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaLivroConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    @Bean
    fun produceFactory(): ProducerFactory<String, RecomendacaoEvent> {

        val props = mapOf<String, Any>(
           ProducerConfig.BOOTSTRAP_SERVERS_CONFIG  to  bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )


        return DefaultKafkaProducerFactory(props)

    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String, RecomendacaoEvent> {
        return KafkaTemplate(produceFactory())
    }


}