package com.bonespirito.kotlinflyway.model

import javax.persistence.*


@Entity
@Table(name = "usuario")
open class Usuario(
        @Id var codigo: Long = 0,
        var nome: String = "",
        var senha: String = "",
        var email: String = ""
) {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = (arrayOf(JoinColumn(name = "codigo_usuario")))
            , inverseJoinColumns = (arrayOf(JoinColumn(name = "codigo_permissao"))))
    val permissoes: List<Permissao>? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}