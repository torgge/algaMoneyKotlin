package com.bonespirito.kotlinflyway.security

import com.bonespirito.kotlinflyway.model.Usuario
import com.bonespirito.kotlinflyway.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserDetailsService : UserDetailsService {

    @Autowired
    private val usuarioRepository: UsuarioRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val usuarioOptional = usuarioRepository!!.findByEmail(email)
        val usuario = usuarioOptional.orElseThrow { UsernameNotFoundException("Usuário e/ou senha incorretos") }
        return User(email, usuario.senha, getPermissoes(usuario))
    }

    private fun getPermissoes(usuario: Usuario): Collection<GrantedAuthority> {
        val authorities = HashSet<SimpleGrantedAuthority>()
        usuario.permissoes?.forEach { p -> authorities.add(SimpleGrantedAuthority(p.descricao.toUpperCase())) }
        return authorities
    }
}