package com.example.mylibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableDiscoveryClient
class MylibraryApplication

@Bean
@LoadBalanced
fun restTemplate(): RestTemplate {
    return RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<MylibraryApplication>(*args)
}
