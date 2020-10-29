package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locador")
public class Locador extends GernericModel {
	private static final long serialVersionUID = 7739179447016740228L;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoa", nullable = false)
	private Pessoa pessoa;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
