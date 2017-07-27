package com.bonespirito.kotlinflyway.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Categoria (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var codigo: Long = 0,
        var nome: String = ""

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Categoria) return false

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}