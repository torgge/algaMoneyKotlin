package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class LancamentoRepositoryImpl: LancamentoRepositoryQuery {

    @PersistenceContext
    val manager: EntityManager = null

    override fun filtrar(lancamentoFilter: LancamentoFilter): List<Lancamento> {
        val builder: CriteriaBuilder = manager.criteriaBuilder
        val criteria = builder.createQuery(Lancamento::class.java)
        val root = criteria.from(Lancamento::class.java)

        predicates = criarRestricaoes(lancamentoFilter, builder, root)


    }

    private abstract fun criarRestricaoes(lancamentoFilter: LancamentoFilter, builder: CriteriaBuilder, root: Root<Lancamento>?): Predicate[] {
        return
    }
}