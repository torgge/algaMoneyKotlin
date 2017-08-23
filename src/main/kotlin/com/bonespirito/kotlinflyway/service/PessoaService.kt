package com.bonespirito.kotlinflyway.service

import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class PessoaService {

    @Autowired
    var pessoaRepository: PessoaRepository? = null

    fun atualizar(codigo: Long, pessoa: Pessoa): Pessoa {
        val pessoaSalva = pessoaRepository?.getOne(codigo) ?: throw EmptyResultDataAccessException(1)
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo")
        return pessoaRepository?.save(pessoaSalva)!!
    }
}