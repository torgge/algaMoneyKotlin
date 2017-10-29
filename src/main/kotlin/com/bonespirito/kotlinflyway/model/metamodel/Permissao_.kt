package com.bonespirito.kotlinflyway.model.metamodel

import com.bonespirito.kotlinflyway.model.Permissao
import javax.annotation.Generated
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel


@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permissao::class)
object Permissao_ {

    @Volatile
    var codigo: SingularAttribute<Permissao, Long>? = null
    @Volatile
    var descricao: SingularAttribute<Permissao, String>? = null

}