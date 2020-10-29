package br.com.ebenezer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ebenezer.model.GernericModel;

import br.com.ebenezer.model.Profissao;
import br.com.ebenezer.model.enums.EnumTipoDocumento;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisica extends GernericModel {
	private static final long serialVersionUID = 7988908017221752560L;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoa", nullable = false, unique = true)
	private Pessoa pessoa;
	
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
	public Profissao getProfissao() {
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}
