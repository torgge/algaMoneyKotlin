package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/pessoas")
open class PessoaResource (val repository: PessoaRepository) {

    @GetMapping
    fun listar() : List<Pessoa> = repository.findAll()

    @PostMapping
    fun criar(@Valid @RequestBody Pessoa:Pessoa, response: HttpServletResponse): ResponseEntity<Pessoa> {

        var PessoaSalva:Pessoa = repository.save(Pessoa)

        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(PessoaSalva.codigo).toUri()
        response.setHeader("Location", uri.toASCIIString())

        return ResponseEntity.created(uri).body(PessoaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo:Long):ResponseEntity<Pessoa> {

        var Pessoa:Pessoa? = repository.findOne(codigo)

        return if (Pessoa != null) ResponseEntity.ok(Pessoa) else ResponseEntity.notFound().build()
    }
}