package com.bonespirito.kotlinflyway.token

import org.apache.catalina.util.ParameterMap
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RefreshTokenCookiePreProcessorFilter : Filter {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        var req = request as HttpServletRequest

        if ("/oauth/token".equals(req.requestURI, ignoreCase = true)
                && "refresh_token" == req.getParameter("grant_type")
                && req.cookies != null) {
                 req.cookies
                    .asSequence()
                    .filter { it.name == "refreshToken" }
                    .map { it.value }
                    .forEach { req = MyServletRequestWrapper(req, it) }
                }

        chain.doFilter(req, response)
    }

    override fun destroy() {

    }

    @Throws(ServletException::class)
    override fun init(arg0: FilterConfig) {

    }

    internal class MyServletRequestWrapper(request: HttpServletRequest, private val refreshToken: String) : HttpServletRequestWrapper(request) {

        override fun getParameterMap(): MutableMap<String, Array<String>>? {
            val map = ParameterMap(request.parameterMap)
            map.put("refresh_token", arrayOf(refreshToken))
            map.isLocked = true
            return map
        }

    }
}