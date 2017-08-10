package com.bonespirito.kotlinflyway.model

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name="categoria")
open class Categoria (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var codigo: Long = 0,
        @NotNull
        @Size(min = 3, max = 20)
        val nome: String?

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