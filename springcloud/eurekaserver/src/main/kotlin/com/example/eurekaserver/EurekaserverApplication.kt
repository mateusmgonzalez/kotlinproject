package com.example.eurekaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableEurekaServer
class EurekaserverApplication

fun main(args: Array<String>) {
	runApplication<EurekaserverApplication>(*args)
}


@RestController
internal class CustomErrorController : ErrorController {
	@RequestMapping(ERROR_MAPPING)
	fun error(): ResponseEntity<Void> {
		return ResponseEntity.notFound().build()
	}

	companion object {
		private const val ERROR_MAPPING = "/error"
	}
}

