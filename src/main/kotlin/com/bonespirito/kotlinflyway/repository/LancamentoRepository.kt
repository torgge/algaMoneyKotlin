package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Lancamento
import org.springframework.data.jpa.repository.JpaRepository

interface LancamentoRepository : JpaRepository<Lancamento, Long>