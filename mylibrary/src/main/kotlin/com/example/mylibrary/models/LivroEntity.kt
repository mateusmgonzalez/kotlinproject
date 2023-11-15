package com.example.mylibrary.models

import jakarta.persistence.*


@Entity
@Table(name = "livros")
data class LivroEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    val nome: String,
    val autor: String,
    val edicao: Int
) {
}
