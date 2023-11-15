package com.example.recomendacoes.config




import com.example.recomendacoes.events.RecomendacaoEvent
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, RecomendacaoEvent> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "recomendacao-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
            ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to JsonDeserializer::class.java.name,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            JsonDeserializer.VALUE_DEFAULT_TYPE to RecomendacaoEvent::class.java
        )

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, RecomendacaoEvent>
    ): ConcurrentKafkaListenerContainerFactory<String, RecomendacaoEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, RecomendacaoEvent>()
        factory.consumerFactory = consumerFactory
        return factory
    }

    @Bean
    fun objectMapper() : ObjectMapper {

        return ObjectMapper().registerKotlinModule()
    }
}
