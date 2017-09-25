package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LancamentoRepositoryQuery {

    fun filtrar(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<Lancamento>?
}