package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import br.com.ebenezer.dao.UsuarioAdministracaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.UsuarioAdministracao;

@ViewScoped
@ManagedBean
public class UsuarioAdministracaoController implements Serializable{
	
	private static final long serialVersionUID = -7025337705013408850L;
	
	private UsuarioAdministracao usuarioAdministracao;
	private List<UsuarioAdministracao> listaUsuarioAdministracao;
	private UsuarioAdministracaoDAO usuarioAdministracaoDAO = new UsuarioAdministracaoDAO();
	
	@PostConstruct
	public void listar() {
		listaUsuarioAdministracao = usuarioAdministracaoDAO.listarUsuarioAdministracao();
	}
	
	public void novo() {
		usuarioAdministracao = new UsuarioAdministracao();
		usuarioAdministracao.setDataCadastro(new Date());
	}

	public void salvar() {
		try {
			usuarioAdministracao.setSenha(DigestUtils.md5Hex(usuarioAdministracao.getSenha()));
			usuarioAdministracaoDAO.merge(usuarioAdministracao);
			this.listar();
			
			PrimeFaces.current().executeScript("PF('dlgUsuario').hide();");
			PrimeFaces.current().ajax().update("frmMVD");
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário Salvo com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
		}
	}
	
	public void desativaAtiva() {
		try {
			usuarioAdministracaoDAO.merge(usuarioAdministracao);
			this.listar();
			
			PrimeFaces.current().executeScript("PF('UsuSituacao').hide();");
			PrimeFaces.current().ajax().update("frmMVD");
			
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário Salvo com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
		}
	}
	
	public void editar(ActionEvent evento) {
		usuarioAdministracao = (UsuarioAdministracao) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}

	public UsuarioAdministracao getUsuarioAdministracao() {
		return usuarioAdministracao;
	}
	public void setUsuarioAdministracao(UsuarioAdministracao usuarioAdministracao) {
		this.usuarioAdministracao = usuarioAdministracao;
	}
	public List<UsuarioAdministracao> getListaUsuarioAdministracao() {
		return listaUsuarioAdministracao;
	}
	public void setListaUsuarioAdministracao(List<UsuarioAdministracao> listaUsuarioAdministracao) {
		this.listaUsuarioAdministracao = listaUsuarioAdministracao;
	}
	public UsuarioAdministracaoDAO getUsuarioAdministracaoDAO() {
		return usuarioAdministracaoDAO;
	}
	public void setUsuarioAdministracaoDAO(UsuarioAdministracaoDAO usuarioAdministracaoDAO) {
		this.usuarioAdministracaoDAO = usuarioAdministracaoDAO;
	}
}
