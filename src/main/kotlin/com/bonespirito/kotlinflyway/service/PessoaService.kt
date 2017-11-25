package com.bonespirito.kotlinflyway.service

import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import org.springframework.beans.BeanUtils
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class PessoaService(val pessoaRepository: PessoaRepository) {

    fun atualizar(codigo: Long, pessoa: Pessoa): Pessoa {
        val pessoaSalva = buscarPessoaPeloCodigo(codigo)
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo")
        return pessoaRepository.save(pessoaSalva)!!
    }

    fun atualizarPropriedadeAtivo(codigo: Long, ativo: Boolean) {
        val pessoaSalva = buscarPessoaPeloCodigo(codigo)
        pessoaSalva.ativo = ativo
        pessoaRepository.save(pessoaSalva)
    }

    private fun buscarPessoaPeloCodigo(codigo: Long): Pessoa {
        val pessoaSalva = pessoaRepository.findOne(codigo) ?: throw EmptyResultDataAccessException(1)
        return pessoaSalva
    }
}