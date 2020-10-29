//package br.com.ebenezer.controller;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.sql.SQLException;
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
//import br.com.ebenezer.dao.CredorDAO;
//import br.com.ebenezer.dao.ReciboAvulsoDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Credor;
//import br.com.ebenezer.model.Funcao;
//import br.com.ebenezer.model.ReciboAvulso;
//import br.com.ebenezer.relatorio.Relatorio;
//import br.com.ebenezer.util.ValorExtenso;
//
//@ManagedBean
//@ViewScoped
//public class ReciboAvulsoController implements Serializable {
//	private static final long serialVersionUID = -3586069217887849799L;
//	
//	@ManagedProperty(value = "#{autenticacaoController}")
//	private AutenticacaoController autenticacao;
//	
//	private ReciboAvulso reciboAvulso;
//	private List<ReciboAvulso> listaReciboAvulso;
//	private List<Funcao> listaReciboAvulsoFiltrados;
//	private ReciboAvulsoDAO reciboAvulsoDAO = new ReciboAvulsoDAO();
//	
//	private List<Credor> listaCredor;
//	private CredorDAO credorDAO = new CredorDAO();
//	
//	private ValorExtenso valorExtenso = new ValorExtenso();
//	
//	private boolean habilitaImpressao = false;
//
//	@PostConstruct
//	public void listar() {
//		try {
//			listaReciboAvulso = reciboAvulsoDAO.listarReciboAvulso();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		reciboAvulso = new ReciboAvulso();
//		reciboAvulso.setDataCadastro(new Date());
//		reciboAvulso.setUsuario(autenticacao.getUsuarioLogado());
//		listaCredor = credorDAO.listarCredorOrdenadoPorNome();
//	}
//	
//	public void gerarValorPorExtenso() {
//		reciboAvulso.setValorPorExtenso(valorExtenso.write(reciboAvulso.getValor()));
//	}
//
//	public void salvar() {
//		try {
//			reciboAvulso.setDescricao(reciboAvulso.getDescricao().toUpperCase());
//			reciboAvulsoDAO.merge(reciboAvulso);
//
//			this.listar();
//			
//			PrimeFaces.current().executeScript("PF('dlgRecibo').hide();");
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Recibo Avulso Gerado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		reciboAvulso = (ReciboAvulso) evento.getComponent().getAttributes().get("reciboAvulsoSelecionado");
//		listaCredor = credorDAO.listarCredorOrdenadoPorNome();
//		habilitaImpressao = false;
//	}
//	
//	public void permitirImpressao(ActionEvent evento) {
//		reciboAvulso = (ReciboAvulso) evento.getComponent().getAttributes().get("reciboAvulsoSelecionado");
//		listaCredor = credorDAO.listarCredorOrdenadoPorNome();
//		
//		habilitaImpressao = true;
//	}
//	
//	public void imprimir()  {
//		try {
//			String caminho = "", nomePdf = "", nomeParametro = "IDRECIBO";
//			
//			caminho = "/relatorios/modulos/recibos/avulsos/reciboAvulso.jasper";
//			nomePdf = "inline;filename=RECIBO-" + reciboAvulso.getId() + ".pdf";
//			Relatorio.gerarPDF(reciboAvulso.getId(), nomeParametro, nomePdf, caminho);
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public ReciboAvulso getReciboAvulso() {
//		return reciboAvulso;
//	}
//	public void setReciboAvulso(ReciboAvulso reciboAvulso) {
//		this.reciboAvulso = reciboAvulso;
//	}
//	public List<ReciboAvulso> getListaReciboAvulso() {
//		return listaReciboAvulso;
//	}
//	public void setListaReciboAvulso(List<ReciboAvulso> listaReciboAvulso) {
//		this.listaReciboAvulso = listaReciboAvulso;
//	}
//	public List<Funcao> getListaReciboAvulsoFiltrados() {
//		return listaReciboAvulsoFiltrados;
//	}
//	public void setListaReciboAvulsoFiltrados(List<Funcao> listaReciboAvulsoFiltrados) {
//		this.listaReciboAvulsoFiltrados = listaReciboAvulsoFiltrados;
//	}
//	public List<Credor> getListaCredor() {
//		return listaCredor;
//	}
//	public void setListaCredor(List<Credor> listaCredor) {
//		this.listaCredor = listaCredor;
//	}
//	public AutenticacaoController getAutenticacao() {
//		return autenticacao;
//	}
//	public void setAutenticacao(AutenticacaoController autenticacao) {
//		this.autenticacao = autenticacao;
//	}
//	public boolean isHabilitaImpressao() {
//		return habilitaImpressao;
//	}
//	public void setHabilitaImpressao(boolean habilitaImpressao) {
//		this.habilitaImpressao = habilitaImpressao;
//	}
//}