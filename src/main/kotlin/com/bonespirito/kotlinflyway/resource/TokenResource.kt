package com.bonespirito.kotlinflyway.resource

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/tokens")
class TokenResource {

    @DeleteMapping("/revoke")
    fun revoke(req : HttpServletRequest, resp : HttpServletResponse) {

        val cookie = Cookie("refreshToken", null)
        cookie.isHttpOnly = true
        cookie.secure = false // TODO: Em produção será true
        cookie.path = req.contextPath + "/oauth/token"
        cookie.maxAge = 0

        resp.addCookie(cookie)
        resp.status = HttpStatus.NO_CONTENT.value()
    }
}