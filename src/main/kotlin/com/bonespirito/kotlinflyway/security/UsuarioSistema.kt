package com.bonespirito.kotlinflyway.security

import com.bonespirito.kotlinflyway.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


class UsuarioSistema(username: String?, password: String?, authorities: MutableCollection<out GrantedAuthority>?) : User(username, password, authorities) {
    private val usuario: Usuario

    fun getUsuario() = this.usuario

    init {
        usuario = Usuario(0, username.toString(), password.toString(), username.toString())
    }
}