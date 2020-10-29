package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoajuridicarepresentante")
public class PessoaJuridicaRepresentante extends GernericModel {
	private static final long serialVersionUID = 7730261882657710623L;

	// ESTÁ ATRELADO AO UM CNPJ
	@ManyToOne
	@JoinColumn(name = "pessoaJuridica", nullable = false)
	private PessoaJuridica pessoaJuridica;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoaFisica", nullable = false)
	private PessoaFisica pessoaFisica;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public PessoaJuridica getPessoaJuridica() {
		if (pessoaJuridica == null) {
			pessoaJuridica = new PessoaJuridica();
		}
		return pessoaJuridica;
	}
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	public PessoaFisica getPessoaFisica() {
		if (pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
		}
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
