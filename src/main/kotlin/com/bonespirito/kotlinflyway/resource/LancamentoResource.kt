package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.exceptionhandler.AlgamoneyExceptionHandler.Erro
import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import com.bonespirito.kotlinflyway.service.LancamentoService
import com.bonespirito.kotlinflyway.service.exception.PessoaInexistenteOuInativaException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/lancamentos")
class LancamentoResource(val repository: LancamentoRepository) {

    @Autowired
    var publisher: ApplicationEventPublisher? = null

    @Autowired
    val lancamentoService: LancamentoService? = null

    @Autowired
    val messageSource: MessageSource? = null

    @GetMapping
    fun pesquisar(lancamentoFilter: LancamentoFilter): List<Lancamento> {
        return repository.filtrar(lancamentoFilter)
    }

    @GetMapping
    fun listar(): List<Lancamento> = repository.findAll()

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo: Long): ResponseEntity<Lancamento> {
        val lancamento = repository.findOne(codigo)
        return if (lancamento != null) ResponseEntity.ok(lancamento) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun criar(@Valid @RequestBody lancamento: Lancamento, response: HttpServletResponse): ResponseEntity<Lancamento> {
        val lancamentoSalvo = lancamentoService?.salvar(lancamento)

        publisher?.publishEvent(
                RecursoCriadoEvent(
                        this,
                        response,
                        lancamentoSalvo?.codigo
                ))

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo)
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException::class)
    fun handlePessoaInexistenteOuInativaException(ex: PessoaInexistenteOuInativaException): ResponseEntity<Any> {
        val mensagemUsuario = messageSource?.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale())
        val mensagemDesenvolvedor = ex.toString()
        val erros = Arrays.asList(Erro(mensagemUsuario, mensagemDesenvolvedor))
        return ResponseEntity.badRequest().body(erros)
    }
}

