package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.ebenezer.dao.TipoUnidadeInstituicaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.TipoUnidadeInstituicao;

@ViewScoped
@ManagedBean
public class TipoUnidadeInstituicaoController implements Serializable{
	
	private static final long serialVersionUID = 681582522335808158L;
	
	private TipoUnidadeInstituicao tipoUnidadeInstituicao;
	private List<TipoUnidadeInstituicao> listaTipoUniInstituicao;
	private TipoUnidadeInstituicaoDAO unidadeInstituicaoDAO = new TipoUnidadeInstituicaoDAO();
	
	@PostConstruct
	public void listar() {
		listaTipoUniInstituicao = unidadeInstituicaoDAO.listar();
	}
	
	public void novo() {
		tipoUnidadeInstituicao = new TipoUnidadeInstituicao();
		tipoUnidadeInstituicao.setDataCadastro(new Date());
	}

	public void salvar() {
		try {
			unidadeInstituicaoDAO.merge(tipoUnidadeInstituicao);
			this.listar();
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Tipo de Igreja Salvo com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
		}
	}
	
	public void editar(ActionEvent evento) {
		tipoUnidadeInstituicao = (TipoUnidadeInstituicao) evento.getComponent().getAttributes().get("tipoUnidadeInstituicaoSelecionada");
	}

	public TipoUnidadeInstituicao getTipoUnidadeInstituicao() {
		return tipoUnidadeInstituicao;
	}

	public void setTipoUnidadeInstituicao(TipoUnidadeInstituicao tipoUnidadeInstituicao) {
		this.tipoUnidadeInstituicao = tipoUnidadeInstituicao;
	}

	public List<TipoUnidadeInstituicao> getListaTipoUniInstituicao() {
		return listaTipoUniInstituicao;
	}

	public void setListaTipoUniInstituicao(List<TipoUnidadeInstituicao> listaTipoUniInstituicao) {
		this.listaTipoUniInstituicao = listaTipoUniInstituicao;
	}

}
