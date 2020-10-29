//package br.com.ebenezer.seguranca;
//
//import javax.faces.event.PhaseEvent;
//import javax.faces.event.PhaseId;
//import javax.faces.event.PhaseListener;
//
//import org.omnifaces.util.Faces;
//
//import br.com.ebenezer.controller.AutenticacaoController;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
////import br.com.ebenezer.model.Usuario;
//import br.com.ebenezer.model.enums.EnumSituacao;

//public class AutenticacaoPhaseListener implements PhaseListener {
//	private static final long serialVersionUID = -2890407072675249783L;
//
//	@Override
//	public void afterPhase(PhaseEvent event) {
//		try {
//			String paginaAtual = Faces.getViewId();
//
//			boolean ehPaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
//
//			if (!ehPaginaAutenticacao) {
//
//				AutenticacaoController autenticaBean = Faces.getSessionAttribute("autenticacaoController");
//
//				if (autenticaBean == null) {
//					Faces.navigate("/view/paginas/autenticacao/autenticacao.xhtml");
//					MsgFeedBackUsuario.AdicionaMensagemErro("Usuário(a) não Autenticado!");
//					return;
//				}
//
//				Usuario usuario = autenticaBean.getUsuarioLogado();
//
//				// Aqui o cara tem que ter função, se não tiver, não loga
//				if (usuario.getLogin() == null && usuario.getSenha() == null && usuario.getSituacao() != EnumSituacao.ATIVO) {
//					MsgFeedBackUsuario.AdicionaMensagemErro("Usuário(a) não Autenticado!");
//					Faces.navigate("/view/paginas/autenticacao/autenticacao.xhtml");
//					return;
//				}
//			}
//
//		} catch (RuntimeException ex) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Usuário/Senha Inválidos!");
//		}
//	}
//
//	@Override
//	public void beforePhase(PhaseEvent event) {
//	}
//
//	@Override
//	public PhaseId getPhaseId() {
//		return PhaseId.RESTORE_VIEW;
//	}

