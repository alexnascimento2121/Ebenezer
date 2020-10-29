//package br.com.ebenezer.controller;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
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
//import br.com.ebenezer.dao.ContratoDAO;
//import br.com.ebenezer.dao.ContratoLocatarioConjuntoDAO;
//import br.com.ebenezer.dao.ContratoParcelaDAO;
//import br.com.ebenezer.dao.FiadorDAO;
//import br.com.ebenezer.dao.ImovelDAO;
//import br.com.ebenezer.dao.LocadorDAO;
//import br.com.ebenezer.dao.LocatarioDAO;
//import br.com.ebenezer.dao.PessoaDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Contrato;
//import br.com.ebenezer.model.ContratoLocatarioConjunto;
//import br.com.ebenezer.model.ContratoParcela;
//import br.com.ebenezer.model.Fiador;
//import br.com.ebenezer.model.Imovel;
//import br.com.ebenezer.model.Locador;
//import br.com.ebenezer.model.Locatario;
//import br.com.ebenezer.model.Pessoa;
//import br.com.ebenezer.model.enums.EnumSituacaoParcelaContrato;
//import br.com.ebenezer.webservice.ConsultaCEP;
//
//@ManagedBean
//@ViewScoped
//public class ContratoController implements Serializable {
//	private static final long serialVersionUID = 166577705190959862L;
//	
//	@ManagedProperty(value = "#{autenticacaoController}")
//	private AutenticacaoController autenticacao;
//	
//	private Contrato contrato;
//	private List<Contrato> listaContrato;
//	private List<Contrato> listaContratoFiltrado;
//	private ContratoDAO contratoDAO = new ContratoDAO();
//	
//	private List<Locador> listaLocador;
//	private LocadorDAO locadorDAO = new LocadorDAO();
//	
//	private List<Locatario> listaLocatario;
//	private LocatarioDAO locatarioDAO = new LocatarioDAO();
//	
//	private List<Fiador> listaFiador;
//	private FiadorDAO fiadorDAO = new FiadorDAO();
//	
//	private ContratoParcela parcela;
//	private List<ContratoParcela> listaParcela;
//	private ContratoParcelaDAO parcelaDAO = new ContratoParcelaDAO();
//	
//	private boolean habilitaEdicao = false;
//	private String situacaoContratoTemporario = null, pesquisaStatus = "";
//	
//	private Imovel imovel;
//	private List<Imovel> listaImovel;
//	private ImovelDAO imovelDAO = new ImovelDAO();
//	
//	private ContratoLocatarioConjunto locatarioConjunto;
//	private List<ContratoLocatarioConjunto> listaLocatarioConjunto;
//	private ContratoLocatarioConjuntoDAO locatarioConjuntoDAO = new ContratoLocatarioConjuntoDAO();
//	
//	private List<Pessoa> listaPessoa;
//	private PessoaDAO pessoaDAO = new PessoaDAO();
//	
//	@PostConstruct
//	public void listar() {
//		listaContrato = contratoDAO.listarContratoEmAberto();
//	}
//	
//	public void carregaListaDadosContrato() {
//		listaLocador = locadorDAO.listarLocadorOrdenadoPorNome();
//		listaLocatario = locatarioDAO.listarLocatarioOrdenadoPorNome();
//		listaFiador = fiadorDAO.listarFiadorOrdenadoPorNome();
//	}
//	
//	public void novoContrato() {
//		contrato = new Contrato();
//		contrato.setDataCadastro(new Date());
//		contrato.setUsuario(autenticacao.getUsuarioLogado());
//		contrato.setDataVencimento(new Date());
////		contrato.setQuantidadeParcela(0);
//		contrato.setValorParcela(new BigDecimal("0"));   
//		contrato.setPorcentagemDesconto(new BigDecimal("0"));
//		contrato.setValorTotalPago(new BigDecimal("0"));
//		contrato.setValorTotalAReceber(new BigDecimal("0"));
//		contrato.setValorTotalContrato(new BigDecimal("0"));
//		contrato.setValorCaucao(new BigDecimal("0"));
//		contrato.setSituacao("RASCUNHO");
//		
//		listaParcela = new ArrayList<>();
//		this.carregaListaDadosContrato();
//		
//		habilitaEdicao = false;
//	}
//	
//	public void geraParcela() {
//		this.novaParcela(contrato.getQuantidadeParcela());
//	}
//	
//	public void novaParcela(int quantidade) {
//		if (contrato.getId() != null) {
//			for (ContratoParcela parcelaTemp : listaParcela) {
//				parcelaDAO.excluir(parcelaTemp);
//			}
//			listaParcela.clear();
//		} else {
//			listaParcela.clear();
//		}
//		contrato.setValorTotalContrato(new BigDecimal("0"));
//		contrato.setValorTotalAReceber(new BigDecimal("0"));
//		
//		Calendar c = Calendar.getInstance();
//		
//		Calendar dataFinal = Calendar.getInstance();
//		dataFinal.setTime(contrato.getDataInicio());
//		
//		for (int i = 0; i < quantidade; i++) {
//			c.setTime(contrato.getDataVencimento()); 
//			if (i == 0) {
//				c.setTime(contrato.getDataVencimento());
//			} else if (i >= 1) {
//				c.add(GregorianCalendar.MONDAY, i);
//			}
//			
//			parcela = new ContratoParcela();
//			parcela.setDataVencimento(c.getTime());
//			parcela.setDataCadastro(new Date());
//			parcela.setNumero(i + 1);
//			parcela.setValorParcela(contrato.getValorParcela());
//			parcela.setValorPago(new BigDecimal("0.00"));
//			parcela.setSituacao(EnumSituacaoParcelaContrato.ABERTO);
//			
//			contrato.setValorTotalContrato(contrato.getValorTotalContrato().add(parcela.getValorParcela()));
//			
//			if (contrato.getId() == null) {
//				listaParcela.add(parcela);
//			} else {
//				parcela.setContrato(contrato);
//				parcelaDAO.merge(parcela);
//				listaParcela = contratoDAO.listarParcelaPorIDContrato(contrato.getId());
//			}
//		}
//		contrato.setValorTotalAReceber(contrato.getValorTotalContrato());
//		
//		dataFinal.add(GregorianCalendar.MONDAY, quantidade);
//		dataFinal.add(GregorianCalendar.DAY_OF_MONTH, -1);
//		contrato.setDataFim(dataFinal.getTime());
//		
////		System.out.println("dataFinal " + dataFinal);
////		System.out.println("listaParcela " + listaParcela.size());
////		System.out.println("quantidade " + quantidade);
////		System.out.println("DataVencimento " + contrato.getDataVencimento());
//	}
//	
//	public void salvarContrato() {
//		try {
//			if (contrato.getId() == null) {
//				contratoDAO.merge(contrato);
//				
//				for (ContratoParcela parcelaTemp : listaParcela) {
//					parcelaTemp.setContrato(contratoDAO.getRetornaId());
//					parcelaDAO.merge(parcelaTemp);
//				}
//			} else {
//				if (contrato.getSituacao().equals("CANCELADO")) {
//					for (ContratoParcela parcelaTemp : listaParcela) {
//						if (parcelaTemp.getSituacao() == EnumSituacaoParcelaContrato.ABERTO) {
//							parcelaTemp.setSituacao(EnumSituacaoParcelaContrato.CANCELADO);
//							parcelaDAO.merge(parcelaTemp);
//						}
//					}
//					
//				} else if (contrato.getSituacao().equals("CONCLUÍDO")) {
//					int total = 0;
//					for (ContratoParcela parcelaTemp : listaParcela) {
//						if (parcelaTemp.getSituacao() == EnumSituacaoParcelaContrato.PAGO) {
//							total = total + 1;
//						}
//					}
//					if (total != listaParcela.size()) {
//						MsgFeedBackUsuario.AdicionaMensagemErro("É Preciso Liquidar todas as Parcelas para Concluir o Contrato!");
//						contrato.setSituacao(situacaoContratoTemporario);
//						return;
//					}
//				}
//				this.recalculaValorPagoContrato();
//				contratoDAO.merge(contrato);
//			}
//			
//			this.listar();
//			
//			PrimeFaces.current().executeScript("PF('dlgContrato').hide();");
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Contrato Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void editarContrato(ActionEvent evento) {
//		contrato = (Contrato) evento.getComponent().getAttributes().get("contratoSelecionado");
//		situacaoContratoTemporario = contrato.getSituacao();
//		listaParcela = contratoDAO.listarParcelaPorIDContrato(contrato.getId());
//		listaImovel = imovelDAO.listarImovelOrdenadoPorDono(contrato.getLocador().getId());
//		listaLocatarioConjunto = contratoDAO.listarLocatarioConjuntoPorIdContrato(contrato.getId());
//		this.carregaListaDadosContrato();
//		
//		if (contrato.getSituacao().equals("RASCUNHO")) {
//			habilitaEdicao = false;
//		} else {
//			habilitaEdicao = true;
//		}
//		
//	}	
//	
//	public void liquidaParcela(ActionEvent evento) {
//		parcela = (ContratoParcela) evento.getComponent().getAttributes().get("parcelaSelecionada");
//		parcela.setValorPago(parcela.getValorParcela());
//	}
//	
//	public void recalculaValorPagoContrato() {
//		contrato.setValorTotalPago(new BigDecimal("0.00"));
//		contrato.setValorTotalAReceber(contrato.getValorTotalContrato());
//		
//		for (ContratoParcela parcelaTemp : listaParcela) {
//			if (parcelaTemp.getSituacao() == EnumSituacaoParcelaContrato.PAGO) {
//				contrato.setValorTotalPago(contrato.getValorTotalPago().add(parcelaTemp.getValorPago()));
//				contrato.setValorTotalAReceber(contrato.getValorTotalAReceber().subtract(contrato.getValorParcela()));
//			}
//		}
//		
//		this.finalizaContratoAutomatico();
//		contratoDAO.merge(contrato);
//		this.listar();
//	}
//	
//	public void salvarParcela() {
//		try {
//			if (parcela.getValorPago().doubleValue() < parcela.getValorParcela().doubleValue()) {
//				MsgFeedBackUsuario.AdicionaMensagemErro("O valor da parcela deve ser igual ou maior que o valor da mesma!");
//				return;
//			}
//			
//			parcela.setSituacao(EnumSituacaoParcelaContrato.PAGO);
//			parcelaDAO.merge(parcela);
//			listaParcela = contratoDAO.listarParcelaPorIDContrato(contrato.getId());
//			this.recalculaValorPagoContrato();
//			
//			PrimeFaces.current().executeScript("PF('dlgParcela').hide();");
//			PrimeFaces.current().ajax().update("frmMVD");
//
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Parcela Liquidada com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void finalizaContratoAutomatico() {
//		int contador = 0;
//		for (ContratoParcela parcelaTemp : listaParcela) {
//			if (parcelaTemp.getSituacao() == EnumSituacaoParcelaContrato.PAGO) {
//				contador = contador + 1;
//			}
//		}
//		if (contador == listaParcela.size()) {
//			contrato.setSituacao("CONCLUÍDO");
//		} 
//	}
//	
//	public void pesquisar() {
//		listaContrato = contratoDAO.listarContratosPorSituacao(pesquisaStatus);
//	}
//	
//	public void novoImovel() {
//		imovel = new Imovel();
//		imovel.setDataCadastro(new Date());
//		imovel.setUsuario(autenticacao.getUsuarioLogado());
//		imovel.setDono(contrato.getLocador());
//	}
//	
//	public void pesquisaImovelPorLocador() {
//		listaImovel = imovelDAO.listarImovelOrdenadoPorDono(contrato.getLocador().getId());
//	}
//	
//	public void consultaCEPImovel()  {
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
//	public void salvarImovel() {
//		try {
//			imovel.setDescricao(imovel.getDescricao().toUpperCase());
//			imovel.setDataUltimaAtualizacao(new Date());
//			imovelDAO.merge(imovel);
//			listaImovel = imovelDAO.listarImovelOrdenadoPorDono(contrato.getLocador().getId());
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
//	public void novoLocatarioConjunto() {
//		locatarioConjunto = new ContratoLocatarioConjunto();
//		locatarioConjunto.setDataCadastro(new Date());
//		locatarioConjunto.setContrato(contrato);
//		listaPessoa = pessoaDAO.listarPessoaOrdenadoPorNome();
//	}
//	
//	public void excluirLocatarioConjunto(ActionEvent evento) {
//		locatarioConjunto = (ContratoLocatarioConjunto) evento.getComponent().getAttributes().get("locatarioConjuntoSelecionado");
//		try {
//			if (contrato.getId() == null) {
//				int achou = -1;
//				for (int posicao = 0; posicao < listaLocatarioConjunto.size(); posicao++) {
//					if (listaLocatarioConjunto.get(posicao).getPessoa().equals(locatarioConjunto.getPessoa())) {
//						achou = posicao;
//					}
//				}
//				if (achou > -1) {
//					listaLocatarioConjunto.remove(achou);
//				}
//			} else {
//				locatarioConjuntoDAO.excluir(locatarioConjunto);
//				listaLocatarioConjunto = contratoDAO.listarLocatarioConjuntoPorIdContrato(contrato.getId());
//			}
//			PrimeFaces.current().executeScript("PF('dlgContrato').initPosition();");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void salvarLocatarioConjunto() {
//		try {
//			locatarioConjuntoDAO.merge(locatarioConjunto);
//			listaLocatarioConjunto = contratoDAO.listarLocatarioConjuntoPorIdContrato(contrato.getId());
//
//			PrimeFaces.current().executeScript("PF('dlgContrato').initPosition(); PF('dlgLocatarioConjunte').hide();");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public Contrato getContrato() {
//		return contrato;
//	}
//	public void setContrato(Contrato contrato) {
//		this.contrato = contrato;
//	}
//	public List<Contrato> getListaContrato() {
//		return listaContrato;
//	}
//	public void setListaContrato(List<Contrato> listaContrato) {
//		this.listaContrato = listaContrato;
//	}
//	public List<Contrato> getListaContratoFiltrado() {
//		return listaContratoFiltrado;
//	}
//	public void setListaContratoFiltrado(List<Contrato> listaContratoFiltrado) {
//		this.listaContratoFiltrado = listaContratoFiltrado;
//	}
//	public ContratoParcela getParcela() {
//		return parcela;
//	}
//	public void setParcela(ContratoParcela parcela) {
//		this.parcela = parcela;
//	}
//	public List<ContratoParcela> getListaParcela() {
//		return listaParcela;
//	}
//	public void setListaParcela(List<ContratoParcela> listaParcela) {
//		this.listaParcela = listaParcela;
//	}
//	public AutenticacaoController getAutenticacao() {
//		return autenticacao;
//	}
//	public void setAutenticacao(AutenticacaoController autenticacao) {
//		this.autenticacao = autenticacao;
//	}
//	public List<Locador> getListaLocador() {
//		return listaLocador;
//	}
//	public void setListaLocador(List<Locador> listaLocador) {
//		this.listaLocador = listaLocador;
//	}
//	public List<Locatario> getListaLocatario() {
//		return listaLocatario;
//	}
//	public void setListaLocatario(List<Locatario> listaLocatario) {
//		this.listaLocatario = listaLocatario;
//	}
//	public boolean isHabilitaEdicao() {
//		return habilitaEdicao;
//	}
//	public String getPesquisaStatus() {
//		return pesquisaStatus;
//	}
//	public void setPesquisaStatus(String pesquisaStatus) {
//		this.pesquisaStatus = pesquisaStatus;
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
//	public List<Fiador> getListaFiador() {
//		return listaFiador;
//	}
//	public void setListaFiador(List<Fiador> listaFiador) {
//		this.listaFiador = listaFiador;
//	}
//	public ContratoLocatarioConjunto getLocatarioConjunto() {
//		return locatarioConjunto;
//	}
//	public void setLocatarioConjunto(ContratoLocatarioConjunto locatarioConjunto) {
//		this.locatarioConjunto = locatarioConjunto;
//	}
//	public List<ContratoLocatarioConjunto> getListaLocatarioConjunto() {
//		return listaLocatarioConjunto;
//	}
//	public void setListaLocatarioConjunto(List<ContratoLocatarioConjunto> listaLocatarioConjunto) {
//		this.listaLocatarioConjunto = listaLocatarioConjunto;
//	}
//	public List<Pessoa> getListaPessoa() {
//		return listaPessoa;
//	}
//	public void setListaPessoa(List<Pessoa> listaPessoa) {
//		this.listaPessoa = listaPessoa;
//	}
//}
