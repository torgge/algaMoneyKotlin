package com.bonespirito.kotlinflyway.exceptionhandler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


open class AlgamoneyExceptionHandler: ResponseEntityExceptionHandler() {

    @Autowired
    var messageSource: MessageSource? = null

    @Override
    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any>? {
        val mensagemUsuario = messageSource?.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale())
        val mensagemDesenvolvedor = ex.cause.toString()
        return handleExceptionInternal(ex, Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request)
    }

    class Erro(val mensagemUsuario: String?, val mensagemDesenvolvedor: String)
}