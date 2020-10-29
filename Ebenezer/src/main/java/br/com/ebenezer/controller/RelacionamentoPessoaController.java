package br.com.ebenezer.controller;

import java.util.Date;

import br.com.ebenezer.dao.PessoaRelacionamentoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.PessoaFisica;
import br.com.ebenezer.model.PessoaRelacionamento;

public class RelacionamentoPessoaController {

	private PessoaRelacionamento relacionamento;
	private PessoaRelacionamentoDAO relacionamentoDAO = new PessoaRelacionamentoDAO();

	public void verificaRelacionamento(PessoaFisica esposo, PessoaFisica esposa) {
		relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(esposo.getId());
		if (relacionamento == null) {
			this.gravaRelacionamento(esposo, esposa);
		} else {
			relacionamento.setPessoa(esposo);
			relacionamento.setConjuge(esposa);
			relacionamentoDAO.merge(relacionamento);
		}
		
		relacionamento = relacionamentoDAO.pesquisaRelacionamentoDireitaPorIdPessoa(esposo.getId());
		if (relacionamento == null) {
			this.gravaRelacionamento(esposa, esposo);
		} else {
			relacionamento.setPessoa(esposa);
			relacionamento.setConjuge(esposo);
			relacionamentoDAO.merge(relacionamento);
		}
		
		relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaParaExcluirPorIdPessoa(esposa.getId(), esposo.getId());
		if (relacionamento != null) {
			relacionamentoDAO.excluir(relacionamento);
		}
		
		relacionamento = relacionamentoDAO.pesquisaRelacionamentoDireitaParaExcluirPorIdPessoa(esposo.getId(), esposa.getId());
		if (relacionamento != null) {
			relacionamentoDAO.excluir(relacionamento);
		}
	}

	public void gravaRelacionamento(PessoaFisica marido, PessoaFisica esposa) {
		try {
			relacionamento = new PessoaRelacionamento();
			relacionamento.setDataCadastro(new Date());
			relacionamento.setPessoa(marido);
			relacionamento.setConjuge(esposa);
			relacionamentoDAO.merge(relacionamento);

		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

}
