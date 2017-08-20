package com.bonespirito.kotlinflyway.exceptionhandler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@ControllerAdvice
class AlgamoneyExceptionHandler : ResponseEntityExceptionHandler() {

    @Autowired
    var messageSource: MessageSource? = null

    @Override
    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any>? {
        val mensagemUsuario = messageSource?.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale())
        val mensagemDesenvolvedor = ex.cause.toString()
        val erros: List<Erro> = Arrays.asList(Erro(mensagemUsuario, mensagemDesenvolvedor))
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request)
    }

    @Override
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val erros = criarListaDeErros(ex.bindingResult)
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request)
    }

    private fun criarListaDeErros(bindingResult: BindingResult): List<Erro> {
        val erros = ArrayList<Erro>()

        for (fieldError in bindingResult.fieldErrors) {
            val mensagemUsuario = messageSource?.getMessage(fieldError, LocaleContextHolder.getLocale())
            val mensagemDesenvolvedor = fieldError.toString()
            erros.add(Erro(mensagemUsuario, mensagemDesenvolvedor))
        }

        return erros
    }


    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException, request: WebRequest): ResponseEntity<Any> {
        val mensagemUsuario = messageSource?.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale())
        val mensagemDesenvolvedor = ex.toString()
        val erros = Arrays.asList(Erro(mensagemUsuario, mensagemDesenvolvedor))
        return handleExceptionInternal(ex, erros, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }


    class Erro(val mensagemUsuario: String?, val mensagemDesenvolvedor: String)
}