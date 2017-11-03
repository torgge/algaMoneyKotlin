package com.bonespirito.kotlinflyway

import com.bonespirito.kotlinflyway.config.property.AlgamoneyApiProperty
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty::class)
class KotlinflywayApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinflywayApplication::class.java, *args)
}
