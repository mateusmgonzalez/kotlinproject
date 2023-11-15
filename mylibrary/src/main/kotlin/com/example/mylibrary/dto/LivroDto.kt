package com.example.mylibrary.dto



data class LivroDto(
    val id: Long,
    val nome: String,
    val autor: String,
    val edicao: Int
) {

    constructor() : this(0, "", "", 0)
}