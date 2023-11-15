package com.example.mylibrary.mapper

import com.example.mylibrary.dto.CreateLivro
import com.example.mylibrary.dto.LivroDto
import com.example.mylibrary.models.LivroEntity

class LivroMapper {
    
    
    
    companion object {
        fun from(livroDto: LivroDto) : LivroEntity {


            return LivroEntity(livroDto.id, livroDto.nome, livroDto.autor, livroDto.edicao)
        }

        fun from(createLivro: CreateLivro) : LivroEntity {


            return LivroEntity(null, createLivro.nome, createLivro.autor, createLivro.edicao)
        }

        fun from(livroEntity: LivroEntity) : LivroDto {

            return  LivroDto(livroEntity.id!!, livroEntity.nome, livroEntity.autor, livroEntity.edicao)
        }
    }
}