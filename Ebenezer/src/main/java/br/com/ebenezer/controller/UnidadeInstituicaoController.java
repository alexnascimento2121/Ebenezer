package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.ebenezer.dao.InstituicaoDAO;
import br.com.ebenezer.dao.TipoUnidadeInstituicaoDAO;
import br.com.ebenezer.dao.UnidadeInstituicaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.Instituicao;
import br.com.ebenezer.model.TipoUnidadeInstituicao;
import br.com.ebenezer.model.UnidadeInstituicao;

@ViewScoped
@ManagedBean
public class UnidadeInstituicaoController implements Serializable{
	
	private static final long serialVersionUID = 2662271948154787993L;
	private UnidadeInstituicao unidadeInstituicao;
	private List<UnidadeInstituicao> listaUnidadeInstituicao;
	private UnidadeInstituicaoDAO unidadeInstituicaoDAO = new UnidadeInstituicaoDAO();
	
	private List<TipoUnidadeInstituicao> listaTipoUnidadeInstituicao;
	private TipoUnidadeInstituicaoDAO tipoUnidadeInstituicaoDAO = new TipoUnidadeInstituicaoDAO();
	
	private List<Instituicao> listaInstituicao;
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	
	@PostConstruct
	public void listar() {
		listaUnidadeInstituicao = unidadeInstituicaoDAO.listar();
	}
	
	public void novo() {
		unidadeInstituicao = new UnidadeInstituicao();
		unidadeInstituicao.setDataCadastro(new Date());
		
		listaTipoUnidadeInstituicao = tipoUnidadeInstituicaoDAO.listar();
		listaInstituicao = instituicaoDAO.listar();
	}

	public void salvar() {
		try {
			unidadeInstituicaoDAO.merge(unidadeInstituicao);
			this.listar();
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Unidade Instituicao Gravado com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
		}
	}
	
	public void editar(ActionEvent evento) {
		unidadeInstituicao = (UnidadeInstituicao) evento.getComponent().getAttributes().get("unidadeInstituicaoSelecionada");
		
		listaTipoUnidadeInstituicao = tipoUnidadeInstituicaoDAO.listar();
		listaInstituicao = instituicaoDAO.listar();
	}

	public UnidadeInstituicao getUnidadeInstituicao() {
		return unidadeInstituicao;
	}
	public void setUnidadeInstituicao(UnidadeInstituicao unidadeInstituicao) {
		this.unidadeInstituicao = unidadeInstituicao;
	}
	public List<UnidadeInstituicao> getListaUnidadeInstituicao() {
		return listaUnidadeInstituicao;
	}
	public void setListaUnidadeInstituicao(List<UnidadeInstituicao> listaUnidadeInstituicao) {
		this.listaUnidadeInstituicao = listaUnidadeInstituicao;
	}
	public List<TipoUnidadeInstituicao> getListaTipoUnidadeInstituicao() {
		return listaTipoUnidadeInstituicao;
	}
	public void setListaTipoUnidadeInstituicao(List<TipoUnidadeInstituicao> listaTipoUnidadeInstituicao) {
		this.listaTipoUnidadeInstituicao = listaTipoUnidadeInstituicao;
	}
	public List<Instituicao> getListaInstituicao() {
		return listaInstituicao;
	}
	public void setListaInstituicao(List<Instituicao> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}	
}
