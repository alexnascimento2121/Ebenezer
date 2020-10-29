package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locadorpessoafisica")
public class LocadorPessoaFisica extends GernericModel {
	private static final long serialVersionUID = 3016149646541707207L;
	
	@ManyToOne
	@JoinColumn(name = "locador", nullable = false)
	private Locador locador;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoaFisica", nullable = false, unique = true)
	private PessoaFisica pessoaFisica;

	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

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
	public Locador getLocador() {
		return locador;
	}
	public void setLocador(Locador locador) {
		this.locador = locador;
	}
}
