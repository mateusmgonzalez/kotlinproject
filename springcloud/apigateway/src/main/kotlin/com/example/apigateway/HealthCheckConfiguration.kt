package com.example.apigateway

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.ReactiveHealthContributor
import org.springframework.boot.actuate.health.ReactiveHealthIndicator
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.logging.Level


@Configuration
class HealthCheckConfiguration(

) {

    private val log: Logger = LoggerFactory.getLogger(HealthCheckConfiguration::class.java)


    @Bean
    @LoadBalanced
    fun loadBalancedWebClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    fun healthcheckMicroservices(): ReactiveHealthContributor? {
        val registry: MutableMap<String, ReactiveHealthIndicator> = LinkedHashMap()
        registry["mylibrary"] = ReactiveHealthIndicator { getHealth("http://mylibrary") }
        registry["recomendacoes"] = ReactiveHealthIndicator { getHealth("http://recomendacoes") }

        return CompositeReactiveHealthContributor.fromMap(registry)
    }

    fun getHealth(baseUrl: String) : Mono<Health> {
        val url = baseUrl + "/actuator/health"
        return loadBalancedWebClientBuilder().build().get().uri(url).retrieve()
            .bodyToMono(String.Companion::class.java)
            .map { Health.Builder().up().build() }
            .onErrorResume { Mono.just(Health.Builder().down(it).build()) }
            .log(log.name, Level.FINE)
    }
}