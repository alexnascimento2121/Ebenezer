package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locatariopessoafisica")
public class LocatarioPessoaFisica extends GernericModel {
	private static final long serialVersionUID = 2084515254035450592L;

	@ManyToOne
	@JoinColumn(name = "locatario", nullable = false)
	private Locatario locatario;

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
	public Locatario getLocatario() {
		return locatario;
	}
	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}
}
