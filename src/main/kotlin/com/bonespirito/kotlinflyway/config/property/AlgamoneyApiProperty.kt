package com.bonespirito.kotlinflyway.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("algamoney")
class AlgamoneyApiProperty {

    var originPermitida = "https://algamoneykotlin.herokuapp.com/"

    val seguranca = Seguranca()

    class Seguranca {

        var isEnableHttps: Boolean = true

    }

}