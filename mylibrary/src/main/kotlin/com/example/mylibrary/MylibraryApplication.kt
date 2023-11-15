package com.example.mylibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MylibraryApplication

fun main(args: Array<String>) {
    runApplication<MylibraryApplication>(*args)
}
