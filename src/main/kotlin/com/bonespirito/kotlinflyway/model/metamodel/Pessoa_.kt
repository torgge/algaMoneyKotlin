package com.bonespirito.kotlinflyway.model.metamodel

import com.bonespirito.kotlinflyway.model.Endereco
import com.bonespirito.kotlinflyway.model.Pessoa

import javax.annotation.Generated
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa::class)
object Pessoa_ {

    @Volatile
    var codigo: SingularAttribute<Pessoa, Long>? = null
    @Volatile
    var ativo: SingularAttribute<Pessoa, Boolean>? = null
    @Volatile
    var endereco: SingularAttribute<Pessoa, Endereco>? = null
    @Volatile
    var nome: SingularAttribute<Pessoa, String>? = null

}