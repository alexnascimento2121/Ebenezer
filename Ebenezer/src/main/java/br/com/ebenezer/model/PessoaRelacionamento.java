package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoarelacionamento")
public class PessoaRelacionamento extends GernericModel {
	private static final long serialVersionUID = 8885854997160103550L;
	
	@ManyToOne
	@JoinColumn(name = "pessoa", nullable = false)
	private PessoaFisica pessoa;
	
	@ManyToOne
	@JoinColumn(name = "conjuge", nullable = false)
	private PessoaFisica conjuge;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public PessoaFisica getPessoa() {
		if (pessoa == null) {
			pessoa = new PessoaFisica();
		}
		return pessoa;
	}
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}
	public PessoaFisica getConjuge() {
		if (conjuge == null) {
			conjuge = new PessoaFisica();
		}
		return conjuge;
	}
	public void setConjuge(PessoaFisica conjuge) {
		this.conjuge = conjuge;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
