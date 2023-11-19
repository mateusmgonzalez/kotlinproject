package com.example.mylibrary.mapper

import com.example.mylibrary.dto.CreateLivro
import com.example.mylibrary.dto.LivroDto
import com.example.mylibrary.models.LivroEntity

class LivroMapper {
    
    
    
    companion object {

        fun from(createLivro: CreateLivro) : LivroEntity {
            return LivroEntity(null, createLivro.titulo, createLivro.autor, createLivro.edicao, createLivro.preco)
        }

        fun from(livroEntity: LivroEntity) : LivroDto {
            return  LivroDto(livroEntity.id!!, livroEntity.titulo, livroEntity.autor, livroEntity.edicao, livroEntity.preco)
        }
    }
}