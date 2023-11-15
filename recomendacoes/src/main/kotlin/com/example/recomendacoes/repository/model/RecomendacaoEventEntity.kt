package com.example.recomendacoes.repository.model

import com.example.recomendacoes.events.RecomendacaoEventType
import jakarta.persistence.*
import java.util.UUID


@Entity
@Table(name = "events_recomendacao")
data class RecomendacaoEventEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id : UUID? = null,
    val eventId: String,
    @Enumerated(value = EnumType.STRING) val recomendacaoEventType: RecomendacaoEventType,
    val nome : String,
    val autor: String
) {
    constructor() : this(null,"",  RecomendacaoEventType.CRIADO, "", "")
    constructor(eventId: String, eventType: RecomendacaoEventType, nome: String, autor: String) : this(null, eventId, eventType, nome, autor)
}
