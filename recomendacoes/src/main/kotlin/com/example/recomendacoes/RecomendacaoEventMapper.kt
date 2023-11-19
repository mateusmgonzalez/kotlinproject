package com.example.recomendacoes

import com.example.recomendacoes.events.RecomendacaoEvent
import com.example.recomendacoes.repository.model.RecomendacaoEventEntity

class RecomendacaoEventMapper {



    companion object {

        fun to(recomendacaoEvent: RecomendacaoEvent) : RecomendacaoEventEntity {
            return RecomendacaoEventEntity(recomendacaoEvent.eventId, recomendacaoEvent.eventType, recomendacaoEvent.titulo, recomendacaoEvent.autor)
        }
    }
}