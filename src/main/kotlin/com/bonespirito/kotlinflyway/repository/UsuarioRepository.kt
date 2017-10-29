package com.bonespirito.kotlinflyway.repository

import com.bonespirito.kotlinflyway.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email : String) : Optional<Usuario>
}