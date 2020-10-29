//package br.com.ebenezer.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import br.com.ebenezer.model.enums.EnumSituacaoParcelaContrato;
//
//@Entity
//@Table(name = "contratoparcela")
//public class ContratoParcela extends GernericModel {
//	private static final long serialVersionUID = 5873233396565577083L;
//
//	@ManyToOne
//	@JoinColumn(name = "contrato", nullable = false)
//	private Contrato contrato;
//	
//	@Column(name="numero")
//	private int numero;
//
//	@Column(name="valorParcela", precision = 19, scale = 4)
//	private BigDecimal valorParcela = new BigDecimal("0.00");
//	
//	@Column(name="valorPago", precision = 19, scale = 4)
//	private BigDecimal valorPago = new BigDecimal("0.00");
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "situacao", length = 10)
//	private EnumSituacaoParcelaContrato situacao;
//	
//	@Column(name="dataVencimento")
//	private Date dataVencimento;
//	
//	@Column(name="dataPagamento")
//	private Date dataPagamento;
//
//	@Column(name="dataCadastro", nullable = false)
//	private Date dataCadastro;
//
//	public Contrato getContrato() {
//		return contrato;
//	}
//	public void setContrato(Contrato contrato) {
//		this.contrato = contrato;
//	}
//	public int getNumero() {
//		return numero;
//	}
//	public void setNumero(int numero) {
//		this.numero = numero;
//	}
//	public BigDecimal getValorParcela() {
//		return valorParcela;
//	}
//	public void setValorParcela(BigDecimal valorParcela) {
//		this.valorParcela = valorParcela;
//	}
//	public BigDecimal getValorPago() {
//		return valorPago;
//	}
//	public void setValorPago(BigDecimal valorPago) {
//		this.valorPago = valorPago;
//	}
//	public EnumSituacaoParcelaContrato getSituacao() {
//		return situacao;
//	}
//	public void setSituacao(EnumSituacaoParcelaContrato situacao) {
//		this.situacao = situacao;
//	}
//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}
//	public Date getDataPagamento() {
//		return dataPagamento;
//	}
//	public void setDataPagamento(Date dataPagamento) {
//		this.dataPagamento = dataPagamento;
//	}
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//}
