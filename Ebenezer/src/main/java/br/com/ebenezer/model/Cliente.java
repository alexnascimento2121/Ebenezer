package br.com.ebenezer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ebenezer.model.enums.EnumTipoDocumento;

@Entity
@Table(name = "membro")
public class Cliente extends GernericModel implements Serializable{
	private static final long serialVersionUID = -838697431026012784L;
	
	@Column(name = "nomeReceita", length = 250, nullable = false)
	private String nomeReceita;
	
	@Column(name = "cargo", length = 250)
	private String cargo;

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
	
	@Column(name = "estadoCivil", length = 15)
	private String estadoCivil;
	
	@ManyToOne
	@JoinColumn(name = "profissao")
	private Profissao profissao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDocumento", length = 5)
	private EnumTipoDocumento tipoDocumento;

	@Column(name = "numeroDocumento", length = 25, nullable = false)
	private String numeroDocumento;
	
	@Column(name = "orgaoEmissor", length = 20, nullable = false)
	private String orgaoEmissor;
	
	@Column(name = "nacionalidade", length = 30)
	private String nacionalidade;
	
	@Column(name = "dataAtualizacao")
	private Date dataAtualizacao;

	public String getNomeReceita() {
		return nomeReceita;
	}
	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Profissao getProfissao() {
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public EnumTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(EnumTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
