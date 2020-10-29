package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locatariopessoajuridica")
public class LocatarioPessoaJuridica extends GernericModel {
	private static final long serialVersionUID = -2397414261587125241L;

	@ManyToOne
	@JoinColumn(name = "locatario", nullable = false)
	private Locatario locatario;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoaJuridica", nullable = false, unique = true)
	private PessoaJuridica pessoaJuridica;

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
