package com.example.mylibrary.exceptions

import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val statusCode: Int,
    val exceptionName: String,
    val path: String,
    val cause: Throwable?
)
