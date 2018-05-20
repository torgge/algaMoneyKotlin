package com.bonespirito.kotlinflyway.config.token

import com.bonespirito.kotlinflyway.security.UsuarioSistema
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import java.util.*


class CustomTokenEnhancer : TokenEnhancer {
	override fun enhance(accessToken: OAuth2AccessToken?, authentication: OAuth2Authentication?): OAuth2AccessToken {
		val usuarioSistema = authentication!!.principal as UsuarioSistema

		val addInfo = HashMap<String, Any>()
		addInfo.put("nome", usuarioSistema.username)

		(accessToken as DefaultOAuth2AccessToken).additionalInformation = addInfo
		return accessToken
	}

}