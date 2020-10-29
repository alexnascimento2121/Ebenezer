package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.ebenezer.dao.FuncaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.Funcao;

@ManagedBean
@ViewScoped
public class FuncaoController implements Serializable {
	private static final long serialVersionUID = -3586069217887849799L;
	
	private Funcao funcao;
	private List<Funcao> listaFuncao;
	private List<Funcao> listaFuncaoFiltrados;
	private FuncaoDAO funcaoDAO = new FuncaoDAO();

	@PostConstruct
	public void listar() {
		try {
			listaFuncao = funcaoDAO.listaFuncaoOrdenada();
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void novo() {
		funcao = new Funcao();
		funcao.setDataCadastro(new Date());
	}

	public void salvar() {
		try {
			funcao.setDescricao(funcao.getDescricao().toUpperCase());
			funcaoDAO.merge(funcao);

			funcao = new Funcao();
			this.listar();
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Função Gravada com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcao = (Funcao) evento.getComponent().getAttributes().get("funcaoSelecionada");

			funcaoDAO.excluir(funcao);
			this.listar();

			MsgFeedBackUsuario.AdicionaMensagemSucesso("Função Excluída com Sucesso.");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
		}
	}

	public void editar(ActionEvent evento) {
		funcao = (Funcao) evento.getComponent().getAttributes().get("funcaoSelecionada");
	}
	
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public List<Funcao> getListaFuncao() {
		return listaFuncao;
	}
	public void setListaFuncao(List<Funcao> listaFuncao) {
		this.listaFuncao = listaFuncao;
	}
	public List<Funcao> getListaFuncaoFiltrados() {
		return listaFuncaoFiltrados;
	}
	public void setListaFuncaoFiltrados(List<Funcao> listaFuncaoFiltrados) {
		this.listaFuncaoFiltrados = listaFuncaoFiltrados;
	}
}