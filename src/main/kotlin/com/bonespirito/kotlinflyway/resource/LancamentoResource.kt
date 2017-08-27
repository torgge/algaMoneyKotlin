package com.bonespirito.kotlinflyway.resource

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lancamentos")
class LancamentoResource(val repository: LancamentoRepository) {

    @GetMapping
    fun listar(): List<Lancamento> = repository.findAll()

    @GetMapping("/{codigo}")
    fun buscaPeloCodigo(@PathVariable codigo: Long): ResponseEntity<Lancamento> {
        val lancamento = repository.findOne(codigo)
        return if (lancamento != null) ResponseEntity.ok(lancamento) else ResponseEntity.notFound().build()
    }
}

