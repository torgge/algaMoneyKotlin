package com.bonespirito.kotlinflyway.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties("algamoney")
class AlgamoneyApiProperty {

    private var originPermitida = "http://localhost:8080"

    private val seguranca = Seguranca()

    fun getSeguranca() = this.seguranca
    fun getOriginPermitida() = this.originPermitida
    fun setOriginPermitida(originPermitida: String) {
        this.originPermitida = originPermitida
    }

    class Seguranca {

        private var enableHttps: Boolean = false

        fun setEnableHttps(enableHttps: Boolean) {
            this.enableHttps = enableHttps
        }

        fun isEnableHttps() = this.enableHttps

    }

}