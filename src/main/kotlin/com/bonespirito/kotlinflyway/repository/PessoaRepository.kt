package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Pessoa
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface PessoaRepository : JpaRepository<Pessoa, Long> {
    fun findByNomeContaining(nome: String, pageable: Pageable): Page<Pessoa>
}
