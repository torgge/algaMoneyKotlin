package com.bonespirito.kotlinflyway.token


import com.bonespirito.kotlinflyway.config.property.AlgamoneyApiProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice
class RefreshTokenPostProcessor : ResponseBodyAdvice<OAuth2AccessToken> {

    @Autowired
    lateinit private var algamoneyApiProperty: AlgamoneyApiProperty

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.method.name == "postAccessToken"
    }

    override fun beforeBodyWrite(body: OAuth2AccessToken, returnType: MethodParameter,
                                 selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>,
                                 request: ServerHttpRequest, response: ServerHttpResponse): OAuth2AccessToken {

        val req = (request as ServletServerHttpRequest).servletRequest
        val resp = (response as ServletServerHttpResponse).servletResponse

        val token = body as DefaultOAuth2AccessToken

        val refreshToken = body.getRefreshToken().value
        adicionarRefreshTokenNoCookie(refreshToken, req, resp)
        removerRefreshTokenDoBody(token)

        return body
    }

    private fun removerRefreshTokenDoBody(token: DefaultOAuth2AccessToken) {
        token.refreshToken = null
    }

    private fun adicionarRefreshTokenNoCookie(refreshToken: String, req: HttpServletRequest, resp: HttpServletResponse) {
        val refreshTokenCookie = Cookie("refreshToken", refreshToken)
        refreshTokenCookie.isHttpOnly = true
        refreshTokenCookie.secure = algamoneyApiProperty.seguranca.isEnableHttps
        refreshTokenCookie.path = req.contextPath + "/oauth/token"
        refreshTokenCookie.maxAge = 2592000
        resp.addCookie(refreshTokenCookie)
    }
}