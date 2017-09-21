package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.model.Lancamento_
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class LancamentoRepositoryImpl : LancamentoRepositoryQuery {

    @PersistenceContext
    val manager: EntityManager? = null

    override fun filtrar(lancamentoFilter: LancamentoFilter): List<Lancamento> {
        val builder: CriteriaBuilder = manager?.criteriaBuilder!!
        val criteria = builder.createQuery(Lancamento::class.java)
        val root = criteria.from(Lancamento::class.java)

        val predicates = criarRestricoes(lancamentoFilter, builder, root)
        criteria.where(*predicates)

        val query = manager.createQuery(criteria)
        return query.resultList
    }

    private fun criarRestricoes(lancamentoFilter: LancamentoFilter, builder: CriteriaBuilder,
                                root: Root<Lancamento>): Array<Predicate> {
        val predicates = ArrayList<Predicate>()

        if (!StringUtils.isEmpty(lancamentoFilter.descricao)) {
            predicates.add(builder.like(
                    builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.descricao?.toLowerCase() + "%"))
        }

        if (lancamentoFilter.dataVencimentoDe != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.dataVencimentoDe))
        }

        if (lancamentoFilter.dataVencimentoAte != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.dataVencimentoAte))
        }

        return predicates.toTypedArray()
    }
}