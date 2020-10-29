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
public class InstituicaoController implements Serializable{

	private static final long serialVersionUID = -6092522537803062678L;
	private Instituicao instituicao;
	private List<Instituicao> listaInstituicao;
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	
	private UnidadeInstituicao unidadeInstituicao;
	private List<UnidadeInstituicao> listaUnidadeInstituicao;
	private UnidadeInstituicaoDAO unidadeInstituicaoDAO = new UnidadeInstituicaoDAO();
	
	private List<TipoUnidadeInstituicao> listaTipoUniInstituicao;
	private TipoUnidadeInstituicaoDAO tipoUniInstituicaoDAO = new TipoUnidadeInstituicaoDAO();
	
	@PostConstruct
	public void listar() {
		listaInstituicao = instituicaoDAO.listar();
	}
	public void listarTipo() {
		listaTipoUniInstituicao = tipoUniInstituicaoDAO.listar();
	}
	
	public void novo() {
		instituicao = new Instituicao();
		instituicao.setDataCadastro(new Date());
	}

	public void salvarInstituicao() {
		try {
			instituicaoDAO.merge(instituicao);
			this.listar();
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Instituição gravada com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
		}
	}
	
	public void editarInstituicao(ActionEvent evento) {
		instituicao = (Instituicao) evento.getComponent().getAttributes().get("instituicaoSelecionada");
		listaUnidadeInstituicao = unidadeInstituicaoDAO.listarIgrejasSuperUsuario(instituicao.getId());
	}
	
	public void novaUnidadeInstituicao() {
		unidadeInstituicao = new UnidadeInstituicao();
		unidadeInstituicao.setDataCadastro(new Date());
		unidadeInstituicao.setInstituicao(instituicao);
		listaTipoUniInstituicao = tipoUniInstituicaoDAO.listar();
	}

	public void salvarUnidadeInstituicao() {
		try {
			unidadeInstituicaoDAO.merge(unidadeInstituicao);
			listaUnidadeInstituicao = unidadeInstituicaoDAO.listarIgrejasSuperUsuario(instituicao.getId());
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Igreja Salvo com Sucesso!");
	} catch (RuntimeException erro) {
		MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
	}
		}
	

	public void editarIgreja(ActionEvent evento) {
		unidadeInstituicao = (UnidadeInstituicao) evento.getComponent().getAttributes().get("IgrejaSelecionada");
		listaUnidadeInstituicao = unidadeInstituicaoDAO.listar();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public List<Instituicao> getListaInstituicao() {
		return listaInstituicao;
	}
	public void setListaInstituicao(List<Instituicao> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
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
	public List<TipoUnidadeInstituicao> getListaTipoUniInstituicao() {
		return listaTipoUniInstituicao;
	}
	public void setListaTipoUniInstituicao(List<TipoUnidadeInstituicao> listaTipoUniInstituicao) {
		this.listaTipoUniInstituicao = listaTipoUniInstituicao;
	}

}
