package com.example.recomendacoes.repository

import com.example.recomendacoes.repository.model.RecomendacaoEventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface RecomendacaoEventRepository : JpaRepository<RecomendacaoEventEntity, UUID>