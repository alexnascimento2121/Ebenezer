//package br.com.ebenezer.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "contratolocatarioconjunto")
//public class ContratoLocatarioConjunto extends GernericModel {
//	private static final long serialVersionUID = -7917846961472543143L;
//
//	@ManyToOne
//	@JoinColumn(name = "contrato", nullable = false)
//	private Contrato contrato;
//	
//	@ManyToOne
//	@JoinColumn(name = "pessoa", nullable = false)
//	private Pessoa pessoa;
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
//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//}
