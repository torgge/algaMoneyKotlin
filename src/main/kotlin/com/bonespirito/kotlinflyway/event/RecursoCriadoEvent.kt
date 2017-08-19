package com.bonespirito.kotlinflyway.event

import org.springframework.context.ApplicationEvent
import javax.servlet.http.HttpServletResponse

open class RecursoCriadoEvent(source: Any, var response: HttpServletResponse?, var codigo: Long?) : ApplicationEvent(source)