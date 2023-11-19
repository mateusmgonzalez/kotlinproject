package com.example.mylibrary.dto



data class LivroDto(
    val id: Long,
    val titulo: String,
    val autor: String,
    val edicao: Int,
    val preco: Long
) {

    constructor() : this(0, "", "", 0, 0L)
}