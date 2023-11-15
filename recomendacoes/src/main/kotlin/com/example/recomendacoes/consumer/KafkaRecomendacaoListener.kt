package com.example.recomendacoes.consumer

import com.example.recomendacoes.RecomendacaoEventMapper
import com.example.recomendacoes.events.RecomendacaoEvent
import com.example.recomendacoes.repository.RecomendacaoEventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaRecomendacaoListener(@Autowired private val repository: RecomendacaoEventRepository) {


    @KafkaListener(topics = ["livro-adicionado"], groupId = "recomendacao-group")
    fun listen(recomendacaoEvent: RecomendacaoEvent) {
        repository.save(RecomendacaoEventMapper.to(recomendacaoEvent))
    }





}