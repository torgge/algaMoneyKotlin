package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.event.RecursoCriadoEvent
import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/lancamentos")
class LancamentoResource(val repository: LancamentoRepository) {

    @Autowired
    var publisher: ApplicationEventPublisher? = null

    @GetMapping
    fun listar(): List<Lancamento> = repository.findAll()

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo: Long): ResponseEntity<Lancamento> {
        val lancamento = repository.findOne(codigo)
        return if (lancamento != null) ResponseEntity.ok(lancamento) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun criar(@Valid @RequestBody lancamento: Lancamento, response: HttpServletResponse): ResponseEntity<Lancamento> {
        val lancamentoSalvo = repository.save(lancamento)

        publisher?.publishEvent(
                RecursoCriadoEvent(
                        this,
                        response,
                        lancamentoSalvo.codigo
                ))

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo)
    }
}

