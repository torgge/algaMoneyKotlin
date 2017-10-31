package com.bonespirito.kotlinflyway.repository.projection

import com.bonespirito.kotlinflyway.model.TipoLancamento
import java.math.BigDecimal
import java.time.LocalDate


class ResumoLancamento(

        var codigo: Long? = 0,
        var descricao: String? = "",
        var dataVencimento: LocalDate? = LocalDate.now(),
        var dataPagamento: LocalDate? = LocalDate.now(),
        var valor: BigDecimal? = BigDecimal(0),
        var tipo: TipoLancamento? = TipoLancamento.RECEITA,
        var categoria: String? = "",
        var pessoa: String? = ""
)