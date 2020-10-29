package br.com.ebenezer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoajuridica")
public class PessoaJuridica extends GernericModel {
	private static final long serialVersionUID = 7730261882657710623L;
	
	@ManyToOne
	@JoinColumn(name = "pessoa", nullable = false, unique = true)
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "cnae", nullable = false)
	private Cnae cnae;
	
	@Column(name = "numeroInscricaoEstadual", length = 25, nullable = false)
	private String numeroInscricaoEstadual;
	
	public String getNumeroInscricaoEstadual() {
		return numeroInscricaoEstadual;
	}
	public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
		this.numeroInscricaoEstadual = numeroInscricaoEstadual;
	}
	public Cnae getCnae() {
		return cnae;
	}
	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
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
}
