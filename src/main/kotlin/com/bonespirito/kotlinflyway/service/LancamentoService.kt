package com.bonespirito.kotlinflyway.service

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.model.Pessoa
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import com.bonespirito.kotlinflyway.service.exception.PessoaInexistenteOuInativaException
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class LancamentoService {

    @Autowired
    val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    val pessoaRepository: PessoaRepository? = null

    fun salvar(lancamento: Lancamento): Lancamento {

        val pessoaDoLancamento = pessoaRepository?.findOne(lancamento.pessoa?.codigo)
        if (pessoaDoLancamento == null || pessoaDoLancamento.isInativo())
            throw PessoaInexistenteOuInativaException()

            return lancamentoRepository?.save(lancamento)!!
    }

    fun atualizar(codigo: Long?, lancamento: Lancamento): Lancamento? {
        val lancamentoSalvo = buscarLancamentoExistente(codigo)
        if (lancamento.pessoa != lancamentoSalvo.pessoa) {
            validarPessoa(lancamento)
        }

        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo")

        var lancamentoRepo = lancamentoRepository
        return if (lancamentoRepo != null) lancamentoRepo.save(lancamentoSalvo) else null
    }

    private fun validarPessoa(lancamento : Lancamento) {
        var pessoa: Pessoa? = null
        if (lancamento.pessoa?.codigo != null) {
            pessoa = pessoaRepository?.findOne(lancamento.pessoa?.codigo)
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw PessoaInexistenteOuInativaException()
        }
	}

    private fun buscarLancamentoExistente(codigo: Long?): Lancamento {
        return lancamentoRepository?.findOne(codigo) ?: throw IllegalArgumentException()
    }
}
