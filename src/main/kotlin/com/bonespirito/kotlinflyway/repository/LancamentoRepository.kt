package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.lancamento.LancamentoRepositoryQuery
import org.springframework.data.jpa.repository.JpaRepository

interface LancamentoRepository : JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery