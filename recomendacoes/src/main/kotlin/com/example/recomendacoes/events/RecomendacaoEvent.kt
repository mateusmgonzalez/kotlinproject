package com.example.recomendacoes.events

import com.fasterxml.jackson.annotation.JsonCreator


data class RecomendacaoEvent @JsonCreator constructor(
    val eventId: String,
    val nome: String,
    val autor: String,
    val eventType: RecomendacaoEventType
)


