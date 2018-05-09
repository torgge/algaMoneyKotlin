package com.bonespirito.kotlinflyway.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("algamoney")
open class AlgamoneyApiProperty {

    val seguranca = Seguranca()
    val originPermitida = "177.73.102.21"

    class Seguranca(var isEnableHttps: Boolean = false)
}