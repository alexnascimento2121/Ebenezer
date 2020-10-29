package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa extends GernericModel {
	private static final long serialVersionUID = 3638481083310252118L;

	@Column(name = "nomeReceita", length = 250, nullable = false)
	private String nomeReceita;
	
	@Column(name = "apelido", length = 250)
	private String apelido;

	@Column(name = "cpfCnpj", unique = true, length = 18, nullable = false)
	private String cpfCnpj;

	// PF = DATA DE NASCIMENTO || PJ DATA DA CRIAÇÃO DA EMPRESA
	@Column(name = "dataNascimento")
	private Date dataNascimento;
	
	@Column(name = "telefone", length = 20)
	private String telefone;
	
	@Column(name = "email", length = 250)
	private String email;
	
	@Column(name = "cep", length = 12, nullable = false)
	private String cep;
	
	@Column(name = "logradouro", length = 70, nullable = false)
	private String logradouro;
	
	@Column(name = "numero", length = 10, nullable = false)
	private String numero;
	
	@Column(name = "bairro", length = 70, nullable = false)
	private String bairro;
	
	@Column(name = "cidade", length = 70, nullable = false)
	private String cidade;
	
	@Column(name = "estadoSigla", length = 3, nullable = false)
	private String estadoSigla;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public String getNomeReceita() {
		return nomeReceita;
	}
	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
