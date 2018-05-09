package com.bonespirito.kotlinflyway.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("algamoney")
open class AlgamoneyApiProperty {

    val seguranca = Seguranca()
    val originPermitida = "*"

    class Seguranca(var isEnableHttps: Boolean = false)
}