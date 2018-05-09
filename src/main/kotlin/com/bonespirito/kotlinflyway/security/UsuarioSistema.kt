package com.bonespirito.kotlinflyway.security

import com.bonespirito.kotlinflyway.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


class UsuarioSistema(username: String?, password: String?, authorities: MutableCollection<out GrantedAuthority>?) : User(username, password, authorities){
    val usuario = Usuario(0, "ADMIN", password!!, username!!)
}