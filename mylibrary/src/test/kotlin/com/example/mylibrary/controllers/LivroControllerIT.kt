package com.example.mylibrary.controllers

import com.example.mylibrary.dto.CreateLivro
import com.example.mylibrary.models.LivroEntity
import com.example.mylibrary.repository.LivroRepository
import com.example.mylibrary.services.LivroService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.servlet.function.RequestPredicates.contentType
import java.util.*

@SpringBootTest(properties = ["spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration"])
@AutoConfigureMockMvc
class LivroControllerIT( @Autowired val mockMvc: MockMvc, @Autowired val livroService: LivroService) {

    @MockBean
    private lateinit var livroRepository: LivroRepository

    private val obj = ObjectMapper()

    @Test
    fun `create a Livro and return 200`() {
        val createLivro = CreateLivro("test", "test", 1)

        mockMvc.perform(post("/livros")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(obj.writeValueAsString(createLivro)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated)

    }


    @Test
    fun `get a livro when request by passing an id`() {

        val id = 0L

        val livroEntity = LivroEntity(id, "mateus", "mteste", 0)

        `when`(livroRepository.findById(id)).thenReturn(Optional.of(livroEntity))

        mockMvc.get("/livros/$id") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath( "$.id") {value(livroEntity.id)}
                jsonPath( "$.nome") {value(livroEntity.nome)}
                jsonPath( "$.autor") {value(livroEntity.autor)}
                jsonPath( "$.edicao") {value(livroEntity.edicao)}
            }

    }
}