package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.model.Categoria
import com.bonespirito.kotlinflyway.repository.CategoriaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/categorias")
open class CategoriaResource (val repository:CategoriaRepository) {

    @GetMapping
    fun listar() : List<Categoria> = repository.findAll()

    @PostMapping
    fun criar(@Valid @RequestBody categoria:Categoria, response:HttpServletResponse):ResponseEntity<Categoria> {

        var categoriaSalva:Categoria = repository.save(categoria)

        val uri:URI = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSalva.codigo).toUri()
        response.setHeader("Location", uri.toASCIIString())

        return ResponseEntity.created(uri).body(categoriaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo:Long):ResponseEntity<Categoria> {

        var categoria:Categoria? = repository.findOne(codigo)

        return if (categoria != null) ResponseEntity.ok(categoria) else ResponseEntity.notFound().build()
    }

}