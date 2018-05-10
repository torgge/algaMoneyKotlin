package com.bonespirito.kotlinflyway.model

import javax.annotation.Generated
import javax.persistence.metamodel.ListAttribute
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel


@Generated(value = ["org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor"])
@StaticMetamodel(Usuario::class)
object Usuario_ {

    @Volatile
    var senha: SingularAttribute<Usuario, String>? = null
    @Volatile
    var permissoes: ListAttribute<Usuario, Permissao>? = null
    @Volatile
    var codigo: SingularAttribute<Usuario, Long>? = null
    @Volatile
    var nome: SingularAttribute<Usuario, String>? = null
    @Volatile
    var email: SingularAttribute<Usuario, String>? = null

}