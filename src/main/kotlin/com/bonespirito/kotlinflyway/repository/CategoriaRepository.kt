package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepository : JpaRepository<Categoria, Long>