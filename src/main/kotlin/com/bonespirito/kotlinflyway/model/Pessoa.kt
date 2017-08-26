package com.bonespirito.kotlinflyway.model

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "pessoa")
open class Pessoa() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var codigo: Long = 0
    @NotNull(message = "[NOME] não pode ser nulo")
    @Size(min = 1, max = 50, message = "Caracteres mínimo:1 e máximo: 50")
    var nome: String? = null
    var ativo: Boolean = false
    @Embedded
    var endereco: Endereco? = null

    constructor(codigo: Long, nome: String, ativo: Boolean, endereco: Endereco?) : this() {
        this.codigo = codigo
        this.nome = nome
        this.ativo = ativo
        this.endereco = endereco
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Pessoa) return false

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}

@Embeddable
open class Endereco() {
    var logradouro: String = ""
    var numero: String = ""
    var complemento: String = ""
    var bairro: String = ""
    var cep: String = ""
    var cidade: String = ""
    var estado: String = ""

    constructor(logradouro: String, numero: String, complemento: String, bairro: String, cep: String, cidade: String, estado: String) : this() {
        this.logradouro = logradouro
        this.numero = numero
        this.complemento = complemento
        this.bairro = bairro
        this.cep = cep
        this.cidade = cidade
        this.estado = estado
    }
}
