package com.bonespirito.kotlinflyway.cors

import com.bonespirito.kotlinflyway.config.property.AlgamoneyApiProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {

    @Autowired private lateinit var algamoneyApiProperty : AlgamoneyApiProperty

    override fun doFilter(req: ServletRequest?, resp: ServletResponse?, chain: FilterChain?) {
        val request = req as HttpServletRequest
        val response = resp as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", algamoneyApiProperty.getOriginPermitida())
        response.setHeader("Access-Control-Allow-Credentials", "true")

        LoggerFactory.getLogger(CorsFilter::class.java).info("Origin: ${request.getHeader("Origin")}")

        if ("OPTIONS".equals(request.getMethod()) && algamoneyApiProperty.getOriginPermitida().equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept")
            response.setHeader("Access-Control-Max-Age", "3600")

            response.status = HttpServletResponse.SC_OK
        } else {
            chain?.doFilter(req, resp)
        }
    }


    override fun destroy() {}

    override fun init(filterConfig: FilterConfig?) {}
}
