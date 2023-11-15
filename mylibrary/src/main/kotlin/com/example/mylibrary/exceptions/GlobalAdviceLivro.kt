package com.example.mylibrary.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime




@ControllerAdvice
class GlobalAdviceLivro {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exceptionName = ex.javaClass.simpleName,
            path = request.contextPath,
            cause = ex.cause
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            statusCode = HttpStatus.NOT_FOUND.value(),
            exceptionName = ex.javaClass.simpleName,
            path = request.contextPath,
            cause = ex.cause
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}