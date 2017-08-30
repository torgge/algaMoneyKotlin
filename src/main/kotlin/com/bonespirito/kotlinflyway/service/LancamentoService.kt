package com.bonespirito.kotlinflyway.service

import com.bonespirito.kotlinflyway.model.Lancamento
import com.bonespirito.kotlinflyway.repository.LancamentoRepository
import com.bonespirito.kotlinflyway.repository.PessoaRepository
import com.bonespirito.kotlinflyway.service.exception.PessoaInexistenteOuInativaException
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
}
