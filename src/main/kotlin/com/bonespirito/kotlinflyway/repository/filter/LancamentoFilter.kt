package com.bonespirito.kotlinflyway.repository.filter

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class LancamentoFilter {
    var descricao: String
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var dataVencimentoDe: LocalDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var dataVencimentoAte: LocalDate

    constructor(descricao: String, dataVencimentoDe: LocalDate, dataVencimentoAte: LocalDate) {
        this.descricao = descricao
        this.dataVencimentoDe = dataVencimentoDe
        this.dataVencimentoAte = dataVencimentoAte
    }
}