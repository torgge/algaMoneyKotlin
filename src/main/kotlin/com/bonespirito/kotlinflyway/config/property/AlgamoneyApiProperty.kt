package com.bonespirito.kotlinflyway.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("algamoney")
open class AlgamoneyApiProperty {

    val seguranca = Seguranca()
    val originPermitida = "http://localhost:8000"

    class Seguranca(var isEnableHttps: Boolean = false)
}