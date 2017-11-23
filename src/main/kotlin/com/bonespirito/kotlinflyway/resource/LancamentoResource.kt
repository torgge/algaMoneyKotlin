package com.bonespirito.kotlinflyway.resource


import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.exceptionhandler.AlgamoneyExceptionHandler
import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import com.bonespirito.kotlinflyway.repository.projection.ResumoLancamento
import com.bonespirito.kotlinflyway.service.LancamentoService
import com.bonespirito.kotlinflyway.service.exception.PessoaInexistenteOuInativaException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasAnyScope('read')")
    fun pesquisar(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<Lancamento> {
        return repository.filtrar(lancamentoFilter, pageable)!!
    }

    @GetMapping(params = arrayOf("resumo"))
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasAnyScope('read')")
    fun resumir(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<ResumoLancamento> {
        return repository.resumir(lancamentoFilter, pageable)!!
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasAnyScope('read')")
    fun buscaPeloCodigo(@PathVariable codigo: Long): ResponseEntity<Lancamento> {
        val lancamento = repository.findOne(codigo)
        return if (lancamento != null) ResponseEntity.ok(lancamento) else ResponseEntity.notFound().build()
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasAnyScope('write')")
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

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasAnyScope('write')")
    fun remover(@PathVariable codigo: Long) {
        repository.delete(codigo)
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException::class)
    fun handlePessoaInexistenteOuInativaException(ex: PessoaInexistenteOuInativaException): ResponseEntity<Any> {
        val mensagemUsuario = messageSource?.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale())
        val mensagemDesenvolvedor = ex.toString()
        val erros = Arrays.asList(AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor))
        return ResponseEntity.badRequest().body(erros)
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    fun atualizar(@PathVariable codigo: Long?, @Valid @RequestBody lancamento: Lancamento): ResponseEntity<Lancamento> {
        try {
            val lancamentoSalvo = if (lancamentoService != null) lancamentoService?.atualizar(codigo, lancamento) else null
            return ResponseEntity.ok(lancamentoSalvo)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.notFound().build()
        }
    }

}

