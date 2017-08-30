package com.bonespirito.kotlinflyway.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "lancamento")
open class Lancamento() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var codigo: Long = 0
    @NotNull
    var descricao: String? = null
    @NotNull
    @Column(name = "data_vencimento")
    var dataVencimento: LocalDate? = null
    @Column(name = "data_pagamento")
    var dataPagamento: LocalDate? = null
    @NotNull
    var valor: BigDecimal? = null
    var observacao: String = ""
    @NotNull
    @Enumerated(EnumType.STRING)
    var tipo: TipoLancamento? = null
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_categoria")
    var categoria: Categoria? = null
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    var pessoa: Pessoa? = null

    constructor(codigo: Long, descricao: String, dataVencimento: LocalDate?, dataPagamento: LocalDate?, valor: BigDecimal?, observacao: String, tipo: TipoLancamento?, categoria: Categoria?, pessoa: Pessoa?) : this() {
        this.codigo = codigo
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

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo.hashCode()
    }
}
