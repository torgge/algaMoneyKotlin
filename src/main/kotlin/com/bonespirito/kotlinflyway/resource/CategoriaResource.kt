package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.model.Categoria
import com.bonespirito.kotlinflyway.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/categorias")
class CategoriaResource (val repository:CategoriaRepository) {

    @Autowired
    var publisher : ApplicationEventPublisher? = null

    @GetMapping
    fun listar() : List<Categoria> = repository.findAll()

    @PostMapping
    fun criar(@Valid @RequestBody categoria: Categoria, response: HttpServletResponse): ResponseEntity<Categoria> {

        val categoriaSalva = repository.save(categoria)

        publisher?.publishEvent(
                RecursoCriadoEvent( this,
                response,
                categoriaSalva.codigo
        ))

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo:Long):ResponseEntity<Categoria> {

        val categoria = repository.findOne(codigo)

        return if (categoria != null) ResponseEntity.ok(categoria) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable codigo: Long) {
        repository.delete(codigo)
    }

}