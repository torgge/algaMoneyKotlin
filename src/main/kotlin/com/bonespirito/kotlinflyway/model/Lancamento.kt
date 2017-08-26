package com.bonespirito.kotlinflyway.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity(name = "lancamento")
open class Lancamento() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var descricao: String = ""
    @Column(name = "data_vencimento")
    var dataVencimento: LocalDate? = null
    @Column(name = "data_pagamento")
    var dataPagamento: LocalDate? = null
    var valor: BigDecimal? = null
    var observacao: String = ""
    @Enumerated(EnumType.STRING)
    var tipo: TipoLancamento? = null
    @ManyToOne
    @JoinColumn(name = "codigo_categoria")
    var categoria: Categoria? = null
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    var pessoa: Pessoa? = null

    constructor(id: Long, descricao: String, dataVencimento: LocalDate?, dataPagamento: LocalDate?, valor: BigDecimal?, observacao: String, tipo: TipoLancamento?, categoria: Categoria?, pessoa: Pessoa?) : this() {
        this.id = id
        this.descricao = descricao
        this.dataVencimento = dataVencimento
        this.dataPagamento = dataPagamento
        this.valor = valor
        this.observacao = observacao
        this.tipo = tipo
        this.categoria = categoria
        this.pessoa = pessoa
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lancamento) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
