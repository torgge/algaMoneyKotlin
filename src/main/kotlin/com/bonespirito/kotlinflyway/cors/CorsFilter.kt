package com.bonespirito.kotlinflyway.cors

import com.bonespirito.kotlinflyway.config.property.AlgamoneyApiProperty
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

    @Autowired
    private lateinit var algamoneyApiProperty : AlgamoneyApiProperty



    override fun doFilter(req: ServletRequest?, resp: ServletResponse?, chain: FilterChain?) {
        val request = req as HttpServletRequest
        val response = resp as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", algamoneyApiProperty.originPermitida)
        response.setHeader("Access-Control-Allow-Credentials", "true")

        if ("OPTIONS" == request.method && algamoneyApiProperty.originPermitida.equals(request.getHeader("Origin"))) {
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
