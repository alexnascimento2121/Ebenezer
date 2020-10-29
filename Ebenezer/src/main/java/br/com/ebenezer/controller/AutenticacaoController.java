//package br.com.ebenezer.controller;
//
//import java.io.Serializable;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
//import org.apache.commons.codec.digest.DigestUtils;
//
//import br.com.ebenezer.dao.UsuarioDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Usuario;
//import br.com.ebenezer.model.enums.EnumSituacao;
//
//@ManagedBean
//@SessionScoped
//public class AutenticacaoController implements Serializable {
//	private static final long serialVersionUID = -2034441247035181329L;
//	
//	private Usuario usuarioLogado;
//	String tipoDispositivo = null;
//	
//	public Usuario getUsuarioLogado() {
//		if (usuarioLogado == null) {
//			usuarioLogado = new Usuario();
//		}
//		return usuarioLogado;
//	}
//
//	public void setUsuarioLogado(Usuario usuarioLogado) {
//		this.usuarioLogado = usuarioLogado;
//	}
//
//	public String autenticar() {
//		try {
//			UsuarioDAO usuarioDAO = new UsuarioDAO();
//			usuarioLogado = usuarioDAO.autenticar(usuarioLogado.getLogin(),	DigestUtils.md5Hex(usuarioLogado.getSenha()));
//			
//			if (usuarioLogado == null) {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Usuário/Senha Inválidos!");
//				usuarioLogado = null;
//				return "/view/paginas/autenticacao/autenticacao.xhtml?faces-redirect=true";
//				
//			} if (usuarioLogado.getLogin() != null && usuarioLogado.getSenha() != null 
//					&& usuarioLogado.getSituacao() == EnumSituacao.ATIVO) {
//				MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário(a) Autenticado com Sucesso!");
//				return "/view/paginas/principal/dashboard.xhtml?faces-redirect=true";
//			
//			} if (usuarioLogado.getSituacao().getDescricao() != "ATIVO") {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Usuário Não está Ativo!");
//				usuarioLogado = null;
//				return "/view/paginas/autenticacao/autenticacao.xhtml?faces-redirect=true";
//			} 
//			
//			return "/view/paginas/autenticacao/autenticacao.xhtml?faces-redirect=true";
//		} catch (RuntimeException ex) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Usuário/Senha Inválidos!");
//			return "/view/paginas/autenticacao/autenticacao.xhtml?faces-redirect=true";
//		}
//	}
//
//	public String sair() {
//		usuarioLogado = null;
//		MsgFeedBackUsuario.AdicionaMensagemSucesso("Usuário(a) Deslogado com Sucesso!");
//		return "/view/paginas/autenticacao/autenticacao.xhtml?faces-redirect=true";
//	}
//	
//	public String getTipoDispositivo() {
//		return tipoDispositivo;
//	}
//	public void setTipoDispositivo(String tipoDispositivo) {
//		this.tipoDispositivo = tipoDispositivo;
//	}
//}
