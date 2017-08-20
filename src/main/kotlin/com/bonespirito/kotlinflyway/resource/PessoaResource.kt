package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/pessoas")
class PessoaResource (val repository: PessoaRepository) {

    @Autowired
    var publisher : ApplicationEventPublisher? = null

    @GetMapping
    fun listar() : List<Pessoa> = repository.findAll()

    @PostMapping
    fun criar(@Valid @RequestBody Pessoa:Pessoa, response: HttpServletResponse): ResponseEntity<Pessoa> {

        val pessoaSalva = repository.save(Pessoa)
        publisher?.publishEvent(RecursoCriadoEvent(
                source = this,
                response = response,
                codigo = pessoaSalva.codigo
        ))
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo:Long):ResponseEntity<Pessoa> {

        val pessoa = repository.findOne(codigo)

        return if (pessoa != null) ResponseEntity.ok(pessoa) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable codigo: Long) {
        repository.delete(codigo)
    }

}