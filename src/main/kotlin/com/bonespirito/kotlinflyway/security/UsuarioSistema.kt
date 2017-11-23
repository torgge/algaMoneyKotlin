package com.bonespirito.kotlinflyway.security

import com.bonespirito.kotlinflyway.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


class UsuarioSistema : User {
    var usuario : Usuario? = null
    constructor(usuario: Usuario, authorities: MutableCollection<out GrantedAuthority>?) : super(usuario.nome, usuario.senha, authorities){
        this.usuario = usuario
    }
}