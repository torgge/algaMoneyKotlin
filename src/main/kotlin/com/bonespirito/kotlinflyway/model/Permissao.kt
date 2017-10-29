package com.bonespirito.kotlinflyway.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "permissao")
class Permissao() {

    @Id
    var codigo : Long = 0
    var descricao : String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Permissao

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}