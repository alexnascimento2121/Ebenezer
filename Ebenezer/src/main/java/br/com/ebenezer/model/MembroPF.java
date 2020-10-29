package br.com.ebenezer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membropf")
public class MembroPF extends GernericModel implements Serializable {
	private static final long serialVersionUID = 3172816909181300985L;
	
	@ManyToOne
	@JoinColumn(name = "membro", nullable = false)
	private Membro membro;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "pessoaFisica", nullable = false, unique = true)
	private PessoaFisica pessoaFisica;

	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public Membro getMembro() {
		return membro;
	}
	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	public PessoaFisica getPessoaFisica() {
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
