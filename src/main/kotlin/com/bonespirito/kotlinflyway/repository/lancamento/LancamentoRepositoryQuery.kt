package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import com.bonespirito.kotlinflyway.repository.projection.ResumoLancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LancamentoRepositoryQuery {

    fun filtrar(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<Lancamento>?
    fun resumir(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<ResumoLancamento>?
}