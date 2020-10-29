//package br.com.ebenezer.controller;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import javax.faces.event.ActionEvent;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.primefaces.PrimeFaces;
//
//import br.com.ebenezer.dao.FuncionarioDAO;
//import br.com.ebenezer.dao.UsuarioDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Funcionario;
//import br.com.ebenezer.model.Usuario;
//import br.com.ebenezer.model.enums.EnumSituacao;
//
//@ManagedBean
//@ViewScoped
//public class UsuarioController implements Serializable {
//	private static final long serialVersionUID = 2078058701652230423L;
//	
//	private Usuario usuario;
//	private List<Usuario> listaUsuario;
//	private List<Usuario> listaUsuarioFiltrados;
//	private UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//	private List<Funcionario> listaFuncionario;
//	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//
//	@ManagedProperty(value = "#{autenticacaoController}")
//	private AutenticacaoController autenticacao;
//	
//	private List<EnumSituacao> listaEnumSituacao = Arrays.asList(EnumSituacao.values());
//
//	@PostConstruct
//	public void listar() {
//		try {
//			listaUsuario = usuarioDAO.listar();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		try {
//			usuario = new Usuario();
//			usuario.setDataCadastro(new Date());
//			
//			listaFuncionario = funcionarioDAO.listar();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void selecionaLogin() {
//		usuario.setLogin(usuario.getFuncionario().getPessoaFisica().getPessoa().getCpfCnpj());
//	}
//
//	public void salvar() {
//		try {
//			usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()).toUpperCase());
//			usuarioDAO.merge(usuario);
//			this.listar();
//			
//			PrimeFaces.current().executeScript("PF('dlgUsuario').hide();");
//			PrimeFaces.current().ajax().update("frmMVD");
//
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
//		listaFuncionario = funcionarioDAO.listar();
//	}
//	
//	public void atualizaSituacao(ActionEvent evento) {
//		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
//		try {
//			if (usuario.getSituacao() == EnumSituacao.ATIVO) {
//				usuario.setSituacao(EnumSituacao.INATIVO);
//			} else {
//				usuario.setSituacao(EnumSituacao.ATIVO);
//			}
//
//			usuarioDAO.merge(usuario);
//			this.listar();
//			
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário Atualizado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//	public List<Usuario> getListaUsuario() {
//		return listaUsuario;
//	}
//	public void setListaUsuario(List<Usuario> listaUsuario) {
//		this.listaUsuario = listaUsuario;
//	}
//	public List<Usuario> getListaUsuarioFiltrados() {
//		return listaUsuarioFiltrados;
//	}
//	public void setListaUsuarioFiltrados(List<Usuario> listaUsuarioFiltrados) {
//		this.listaUsuarioFiltrados = listaUsuarioFiltrados;
//	}
//	public List<Funcionario> getListaFuncionario() {
//		return listaFuncionario;
//	}
//	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
//		this.listaFuncionario = listaFuncionario;
//	}
//	public AutenticacaoController getAutenticacao() {
//		return autenticacao;
//	}
//	public void setAutenticacao(AutenticacaoController autenticacao) {
//		this.autenticacao = autenticacao;
//	}
//	public List<EnumSituacao> getListaEnumSituacao() {
//		return listaEnumSituacao;
//	}
//	public void setListaEnumSituacao(List<EnumSituacao> listaEnumSituacao) {
//		this.listaEnumSituacao = listaEnumSituacao;
//	}
//}