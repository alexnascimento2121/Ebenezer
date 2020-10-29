//package br.com.ebenezer.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "reciboavulso")
//public class ReciboAvulso extends GernericModel {
//	private static final long serialVersionUID = 1601570524466469048L;
//
//	@ManyToOne
//	@JoinColumn(name = "usuario")
//	private Usuario usuario;
//
//	@ManyToOne
//	@JoinColumn(name = "credorPagou")
//	private Credor credorPagou;
//	
//	@ManyToOne
//	@JoinColumn(name = "credorRecebeu")
//	private Credor credorRecebeu;
//	
//	@Column(name = "descricao", length = 250)
//	private String descricao;
//	
//	@Column(name="valor", precision = 19, scale = 4)
//	private BigDecimal valor;
//	
//	@Column(name = "valorPorExtenso", length = 200)
//	private String valorPorExtenso;
//	
//	@Column(name = "data", nullable = false)
//	private Date data;
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
//	public String getDescricao() {
//		return descricao;
//	}
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//	public BigDecimal getValor() {
//		return valor;
//	}
//	public void setValor(BigDecimal valor) {
//		this.valor = valor;
//	}
//	public String getValorPorExtenso() {
//		return valorPorExtenso;
//	}
//	public void setValorPorExtenso(String valorPorExtenso) {
//		this.valorPorExtenso = valorPorExtenso;
//	}
//	public Date getData() {
//		return data;
//	}
//	public void setData(Date data) {
//		this.data = data;
//	}
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//	public Credor getCredorPagou() {
//		return credorPagou;
//	}
//	public void setCredorPagou(Credor credorPagou) {
//		this.credorPagou = credorPagou;
//	}
//	public Credor getCredorRecebeu() {
//		return credorRecebeu;
//	}
//	public void setCredorRecebeu(Credor credorRecebeu) {
//		this.credorRecebeu = credorRecebeu;
//	}
//}
