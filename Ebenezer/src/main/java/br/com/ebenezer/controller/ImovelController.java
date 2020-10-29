//package br.com.ebenezer.controller;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import javax.faces.event.ActionEvent;
//
//import org.primefaces.PrimeFaces;
//
//import br.com.ebenezer.dao.ImovelDAO;
//import br.com.ebenezer.dao.LocadorDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Imovel;
//import br.com.ebenezer.model.Locador;
//import br.com.ebenezer.webservice.ConsultaCEP;
//
//@ManagedBean
//@ViewScoped
//public class ImovelController implements Serializable {
//	private static final long serialVersionUID = -3586069217887849799L;
//	
//	@ManagedProperty(value = "#{autenticacaoController}")
//	private AutenticacaoController autenticacao;
//	
//	private Imovel imovel;
//	private List<Imovel> listaImovel;
//	private List<Imovel> listaImovelFiltrados;
//	private ImovelDAO imovelDAO = new ImovelDAO();
//	
//	private List<Locador> listaLocador;
//	private LocadorDAO locadorDAO = new LocadorDAO();
//
//	@PostConstruct
//	public void listar() {
//		try {
//			listaImovel = imovelDAO.listarImovelOrdenadoPorDescricao();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		imovel = new Imovel();
//		imovel.setDataCadastro(new Date());
//		imovel.setUsuario(autenticacao.getUsuarioLogado());
//		
//		listaLocador = locadorDAO.listarLocadorOrdenadoPorNome();
//	}
//	
//	public void consultaCEP()  {
//		try {
//			ConsultaCEP consultaCEP = new ConsultaCEP();
//			consultaCEP.pesquisaCEP(imovel.getCep());
//			imovel.setLogradouro(consultaCEP.getLogradouro());
//			imovel.setBairro(consultaCEP.getBairro());
//			imovel.setCidade(consultaCEP.getLocalidade());
//			imovel.setEstadoSigla(consultaCEP.getUf());
//
//		} catch (RuntimeException | IOException erro) {
//		}
//	}
//
//	public void excluir(ActionEvent evento) {
//		imovel = (Imovel) evento.getComponent().getAttributes().get("imovelSelecionado");
//		try {
//
//			imovelDAO.excluir(imovel);
//			this.listar();
//
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Imóvel Excluído com Sucesso.");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//		}
//	}
//	
//	public void salvar() {
//		try {
//			imovel.setDescricao(imovel.getDescricao().toUpperCase());
//			imovel.setDataUltimaAtualizacao(new Date());
//			imovelDAO.merge(imovel);
//			this.listar();
//
//			PrimeFaces.current().executeScript("PF('dlgImovel').hide();");
//			
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Imóvel Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		imovel = (Imovel) evento.getComponent().getAttributes().get("imovelSelecionado");
//		
//		listaLocador = locadorDAO.listarLocadorOrdenadoPorNome();
//	}
//
//	public AutenticacaoController getAutenticacao() {
//		return autenticacao;
//	}
//	public void setAutenticacao(AutenticacaoController autenticacao) {
//		this.autenticacao = autenticacao;
//	}
//	public Imovel getImovel() {
//		return imovel;
//	}
//	public void setImovel(Imovel imovel) {
//		this.imovel = imovel;
//	}
//	public List<Imovel> getListaImovel() {
//		return listaImovel;
//	}
//	public void setListaImovel(List<Imovel> listaImovel) {
//		this.listaImovel = listaImovel;
//	}
//	public List<Imovel> getListaImovelFiltrados() {
//		return listaImovelFiltrados;
//	}
//	public void setListaImovelFiltrados(List<Imovel> listaImovelFiltrados) {
//		this.listaImovelFiltrados = listaImovelFiltrados;
//	}
//	public List<Locador> getListaLocador() {
//		return listaLocador;
//	}
//	public void setListaLocador(List<Locador> listaLocador) {
//		this.listaLocador = listaLocador;
//	}
//}