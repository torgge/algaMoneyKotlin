package com.bonespirito.kotlinflyway.repository.lancamento

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.model.metamodel.Lancamento_
import com.bonespirito.kotlinflyway.repository.filter.LancamentoFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


open class LancamentoRepositoryImpl : LancamentoRepositoryQuery {

    @PersistenceContext
    open var manager: EntityManager? = null

    override fun filtrar(lancamentoFilter: LancamentoFilter, pageable: Pageable): Page<Lancamento>? {
        val builder = manager!!.criteriaBuilder
        val criteria = builder.createQuery(Lancamento::class.java)
        val root = criteria.from(Lancamento::class.java)

        val predicates = criarRestricoes(lancamentoFilter, builder, root)
        criteria.where(*predicates)

        val query = manager!!.createQuery(criteria)
        adicionarRestricoesDePaginacao(query, pageable)


        return PageImpl<Lancamento>(query.resultList, pageable, total(lancamentoFilter))
    }

    private fun adicionarRestricoesDePaginacao(query: TypedQuery<Lancamento>, pageable: Pageable) {
        val paginaAtual = pageable.pageNumber
        val totalRegistrosPorPagina = pageable.pageSize
        val primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina
        query.firstResult = primeiroRegistroDaPagina
        query.maxResults = totalRegistrosPorPagina
    }

    private fun total(lancamentoFilter: LancamentoFilter): Long {
        val builder = manager!!.criteriaBuilder
        val criteria = builder?.createQuery(Long::class.java)
        val root = criteria?.from(Lancamento::class.java)

        val predicates = criarRestricoes(lancamentoFilter, builder!!, root!!)
        criteria.where(*predicates)

        criteria.select(builder.count(root))
        return manager?.createQuery(criteria)!!.singleResult
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