package com.bonespirito.kotlinflyway.event.listener

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletResponse

@Component
class RecursoCriadoListener : ApplicationListener<RecursoCriadoEvent> {
    override fun onApplicationEvent(event: RecursoCriadoEvent?) {
        var response: HttpServletResponse? = event?.response
        var codigo: Long? = event?.codigo

        adicionarHeaderLocation(response, codigo)
    }

    private fun adicionarHeaderLocation(response: HttpServletResponse?, codigo: Long?) {
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri()
        response?.setHeader(
                "Location",
                uri.toASCIIString()
        )
    }
}