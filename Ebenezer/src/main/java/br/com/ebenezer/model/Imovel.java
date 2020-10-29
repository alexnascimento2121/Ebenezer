package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imovel")
public class Imovel extends GernericModel {
	private static final long serialVersionUID = -2508898965465350599L;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "dono")
	private Locador dono;
	
	@Column(name = "descricao", length = 250)
	private String descricao;
	
	@Column(name = "codigoCeron", length = 20)
	private String codigoCeron;
	
	@Column(name = "codigoCaerd", length = 20)
	private String codigoCaerd;
	
	@Column(name = "cep", length = 12)
	private String cep;
	
	@Column(name = "logradouro", length = 70)
	private String logradouro;
	
	@Column(name = "numero", length = 10)
	private String numero;
	
	@Column(name = "bairro", length = 70)
	private String bairro;
	
	@Column(name = "cidade", length = 70)
	private String cidade;
	
	@Column(name = "estadoSigla", length = 3)
	private String estadoSigla;
	
	@Column(name = "situacao", length = 15)
	private String situacao;
	
	@Column(name = "dataUltimaAtualizacao", nullable = false)
	private Date dataUltimaAtualizacao;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Locador getDono() {
		return dono;
	}
	public void setDono(Locador dono) {
		this.dono = dono;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigoCeron() {
		return codigoCeron;
	}
	public void setCodigoCeron(String codigoCeron) {
		this.codigoCeron = codigoCeron;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstadoSigla() {
		return estadoSigla;
	}
	public void setEstadoSigla(String estadoSigla) {
		this.estadoSigla = estadoSigla;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public String getCodigoCaerd() {
		return codigoCaerd;
	}
	public void setCodigoCaerd(String codigoCaerd) {
		this.codigoCaerd = codigoCaerd;
	}
}
