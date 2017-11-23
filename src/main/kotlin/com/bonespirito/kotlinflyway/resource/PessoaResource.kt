package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import com.bonespirito.kotlinflyway.service.PessoaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/pessoas")
class PessoaResource (val repository: PessoaRepository) {

    @Autowired
    var publisher : ApplicationEventPublisher? = null

    @Autowired
    var pessoaService: PessoaService? = null

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasAnyScope('read')")
    fun buscaPeloCodigo(@PathVariable codigo: Long): ResponseEntity<Pessoa> {
        val pessoa = repository.findOne(codigo)
        return if (pessoa != null) ResponseEntity.ok(pessoa) else ResponseEntity.notFound().build()
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasAnyScope('write')")
    fun criar(@Valid @RequestBody pessoa: Pessoa, response: HttpServletResponse): ResponseEntity<Pessoa> {
        val pessoaSalva = repository.save(pessoa)
        publisher?.publishEvent(RecursoCriadoEvent(
                source = this,
                response = response,
                codigo = pessoaSalva.codigo
        ))
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva)
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasAnyScope('write')")
    fun remover(@PathVariable codigo: Long) {
        repository.delete(codigo)
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasAnyScope('write')")
    fun atualizar(@PathVariable codigo: Long, @Valid @RequestBody pessoa: Pessoa): ResponseEntity<Pessoa> {
        val pessoaSalva: Pessoa? = pessoaService?.atualizar(codigo = codigo, pessoa = pessoa)
        return ResponseEntity.ok(pessoaSalva)
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasAnyScope('write')")
            /*parametro ativo nullavel, para ser tratado no exceptionhandler*/
    fun atualizaPropriedadeAtivo(@PathVariable codigo: Long, @RequestBody ativo: Boolean?) {
        /*funcao atualizarPropriedadeAtivo, não aceita propriedade ativo como nula por isso usa-se !!*/
        pessoaService?.atualizarPropriedadeAtivo(codigo, ativo!!)
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasAnyScope('read')")
    fun listar() : List<Pessoa> = repository.findAll()

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
    fun pesquisar(@RequestParam(required = false, defaultValue = "%") nome: String, pageable: Pageable): Page<Pessoa> {
        return repository.findByNomeContaining(nome, pageable)
    }
}
