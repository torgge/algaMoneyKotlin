package com.bonespirito.kotlinflyway.model

import javax.annotation.Generated
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel

@Generated(value = ["org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor"])
@StaticMetamodel(Endereco::class)
object Endereco_ {

    @Volatile
    var cidade: SingularAttribute<Endereco, String>? = null
    @Volatile
    var estado: SingularAttribute<Endereco, String>? = null
    @Volatile
    var complemento: SingularAttribute<Endereco, String>? = null
    @Volatile
    var numero: SingularAttribute<Endereco, String>? = null
    @Volatile
    var logradouro: SingularAttribute<Endereco, String>? = null
    @Volatile
    var bairro: SingularAttribute<Endereco, String>? = null
    @Volatile
    var cep: SingularAttribute<Endereco, String>? = null

}