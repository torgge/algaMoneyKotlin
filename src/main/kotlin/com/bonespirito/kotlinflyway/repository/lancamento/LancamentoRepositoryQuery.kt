package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter

interface LancamentoRepositoryQuery {

    fun filtrar(lancamentoFilter: LancamentoFilter): List<Lancamento>
}