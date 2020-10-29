//package br.com.ebenezer.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "contrato")
//public class Contrato extends GernericModel {
//	private static final long serialVersionUID = 3861870801356093204L;
//	
//	@ManyToOne
//	@JoinColumn(name = "usuario", nullable = false)
//	private Usuario usuario;
//
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "locador", nullable = false)
//	private Locador locador;
//	
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "locatario", nullable = false)
//	private Locatario locatario;
//	
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "fiador")
//	private Fiador fiador;
//	
//	@ManyToOne
//	@JoinColumn(name = "imovel")
//	private Imovel imovel;
//	
//	@Column(name = "dataInicio", nullable = false)
//	private Date dataInicio;
//	
//	@Column(name = "dataFim", nullable = false)
//	private Date dataFim;
//	
//	@Column(name="dataVencimento", nullable = false)
//	private Date dataVencimento;
//	
//	@Column(name="valorParcela", precision = 19, scale = 4)
//	private BigDecimal valorParcela;
//	
//	@Column(name="quantidadeParcela")
//	private int quantidadeParcela ;
//	
//	@Column(name="porcentagemDesconto", precision = 6, scale = 4, nullable = false)
//	private BigDecimal porcentagemDesconto;
//	
//	@Column(name="valorTotalPago", precision = 19, scale = 4)
//	private BigDecimal valorTotalPago;
//	
//	@Column(name="valorTotalAReceber", precision = 19, scale = 4)
//	private BigDecimal valorTotalAReceber;
//	
//	@Column(name="valorTotalContrato", precision = 19, scale = 4)
//	private BigDecimal valorTotalContrato;
//	
//	@Column(name="valorCaucao", precision = 19, scale = 4, nullable = false)
//	private BigDecimal valorCaucao;
//	
//	@Column(name = "situacao", length = 15)
//	private String situacao;
//	
//	@Column(name = "dataCadastro", nullable = false)
//	private Date dataCadastro;
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//	public Locador getLocador() {
//		return locador;
//	}
//	public void setLocador(Locador locador) {
//		this.locador = locador;
//	}
//	public Locatario getLocatario() {
//		return locatario;
//	}
//	public void setLocatario(Locatario locatario) {
//		this.locatario = locatario;
//	}
//	public Date getDataInicio() {
//		return dataInicio;
//	}
//	public void setDataInicio(Date dataInicio) {
//		this.dataInicio = dataInicio;
//	}
//	public Date getDataFim() {
//		return dataFim;
//	}
//	public void setDataFim(Date dataFim) {
//		this.dataFim = dataFim;
//	}
//	public int getQuantidadeParcela() {
//		return quantidadeParcela;
//	}
//	public void setQuantidadeParcela(int quantidadeParcela) {
//		this.quantidadeParcela = quantidadeParcela;
//	}
//	public BigDecimal getValorParcela() {
//		return valorParcela;
//	}
//	public void setValorParcela(BigDecimal valorParcela) {
//		this.valorParcela = valorParcela;
//	}
//	public BigDecimal getValorTotalPago() {
//		return valorTotalPago;
//	}
//	public void setValorTotalPago(BigDecimal valorTotalPago) {
//		this.valorTotalPago = valorTotalPago;
//	}
//	public BigDecimal getValorTotalContrato() {
//		return valorTotalContrato;
//	}
//	public void setValorTotalContrato(BigDecimal valorTotalContrato) {
//		this.valorTotalContrato = valorTotalContrato;
//	}
//	public String getSituacao() {
//		return situacao;
//	}
//	public void setSituacao(String situacao) {
//		this.situacao = situacao;
//	}
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}
//	public BigDecimal getPorcentagemDesconto() {
//		return porcentagemDesconto;
//	}
//	public void setPorcentagemDesconto(BigDecimal porcentagemDesconto) {
//		this.porcentagemDesconto = porcentagemDesconto;
//	}
//	public BigDecimal getValorTotalAReceber() {
//		return valorTotalAReceber;
//	}
//	public void setValorTotalAReceber(BigDecimal valorTotalAReceber) {
//		this.valorTotalAReceber = valorTotalAReceber;
//	}
//	public Imovel getImovel() {
//		return imovel;
//	}
//	public void setImovel(Imovel imovel) {
//		this.imovel = imovel;
//	}
//	public BigDecimal getValorCaucao() {
//		return valorCaucao;
//	}
//	public void setValorCaucao(BigDecimal valorCaucao) {
//		this.valorCaucao = valorCaucao;
//	}
//	public Fiador getFiador() {
//		return fiador;
//	}
//	public void setFiador(Fiador fiador) {
//		this.fiador = fiador;
//	}
//}
