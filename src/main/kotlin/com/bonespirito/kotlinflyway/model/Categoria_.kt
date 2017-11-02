package com.bonespirito.kotlinflyway.model

import javax.annotation.Generated
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria::class)
object Categoria_ {
    @Volatile var codigo: SingularAttribute<Categoria, Long>? = null
    @Volatile var nome: SingularAttribute<Categoria, String>? = null
}