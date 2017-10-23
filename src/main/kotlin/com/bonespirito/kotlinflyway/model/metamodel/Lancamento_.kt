package com.bonespirito.kotlinflyway.model.metamodel

import com.bonespirito.kotlinflyway.model.Categoria
import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.model.TipoLancamento
import java.math.BigDecimal
import java.time.LocalDate
import javax.annotation.Generated
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel


@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento::class)
object Lancamento_ {

    @Volatile
    var codigo: SingularAttribute<Lancamento, Long>? = null
    @Volatile
    var observacao: SingularAttribute<Lancamento, String>? = null
    @Volatile
    var tipo: SingularAttribute<Lancamento, TipoLancamento>? = null
    @Volatile
    var dataPagamento: SingularAttribute<Lancamento, LocalDate>? = null
    @Volatile
    var pessoa: SingularAttribute<Lancamento, Pessoa>? = null
    @Volatile
    var dataVencimento: SingularAttribute<Lancamento, LocalDate>? = null
    @Volatile
    var categoria: SingularAttribute<Lancamento, Categoria>? = null
    @Volatile
    var valor: SingularAttribute<Lancamento, BigDecimal>? = null
    @Volatile
    var descricao: SingularAttribute<Lancamento, String>? = null

}