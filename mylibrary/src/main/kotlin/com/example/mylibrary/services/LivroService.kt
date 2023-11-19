package com.example.mylibrary.services

import com.example.mylibrary.dto.CreateLivro
import com.example.mylibrary.exceptions.NotFoundException
import com.example.mylibrary.dto.LivroDto
import com.example.mylibrary.events.RecomendacaoEvent
import com.example.mylibrary.events.RecomendacaoEventType
import com.example.mylibrary.mapper.LivroMapper
import com.example.mylibrary.repository.LivroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class LivroService(@Autowired private val repository: LivroRepository, @Autowired private val kafkaTemplate: KafkaTemplate<String, RecomendacaoEvent>) {

    @Cacheable(value = ["livroDto"], key = "#id")
    fun getLivroById(id: Long) : LivroDto {
        return LivroMapper.from(repository.findById(id).orElseThrow { NotFoundException("Não encontrei o livro") })
    }


    fun createLivro(createLivro: CreateLivro)  {
        repository.save(LivroMapper.from(createLivro))

        val recomendacaoEvent = RecomendacaoEvent(UUID.randomUUID().toString(), createLivro.titulo, createLivro.autor, RecomendacaoEventType.CRIADO)
        kafkaTemplate.send("livro-adicionado", recomendacaoEvent.eventId, recomendacaoEvent )

    }

    @CachePut(cacheNames = ["livroDto"], key = "#id")
    fun update(id: Long,livroDto: LivroDto)  {
        repository.findById(id)
    }
    @CacheEvict(cacheNames = ["livroDto"], key = "#id", allEntries = true)
    fun delete(id: Long) {
        repository.deleteById(id)
    }


    fun getAll(): MutableList<LivroDto> {
        return repository.findAll().map { LivroMapper.from(it) }.toMutableList()
    }
}