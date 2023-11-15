package com.example.mylibrary.controller

import com.example.mylibrary.dto.CreateLivro
import com.example.mylibrary.dto.LivroDto
import com.example.mylibrary.services.LivroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/livros")
class LivroController(
    @Autowired
    private val service: LivroService
) {

    @PostMapping
    fun create(@RequestBody createLivro: CreateLivro) : ResponseEntity<Void> {

        service.createLivro(createLivro)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun getAll() : ResponseEntity<MutableList<LivroDto>> {

        return ResponseEntity(service.getAll(), HttpStatus.OK)
    }

    @GetMapping( "/{id}")

    fun getLivroById(@PathVariable id: Long) : ResponseEntity<LivroDto> {

        return ResponseEntity(service.getLivroById(id), HttpStatus.OK)
    }

    @PutMapping("/{id}")

    fun update(@PathVariable id: Long, @RequestBody livroDto: LivroDto): ResponseEntity<Void> {
        service.update(id, livroDto)
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/{id}")
    fun  delete(@PathVariable id: Long) : ResponseEntity<Void> {
        service.delete(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


}