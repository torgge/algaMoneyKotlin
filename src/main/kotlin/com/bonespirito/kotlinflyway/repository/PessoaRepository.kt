package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Pessoa
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaRepository : JpaRepository<Pessoa, Long>
