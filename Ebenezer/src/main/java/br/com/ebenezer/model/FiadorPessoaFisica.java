//package br.com.ebenezer.model;
//
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import br.com.ebenezer.model.nova.PessoaFisica;
//
//@Entity
//@Table(name = "fiadorpessoafisica")
//public class FiadorPessoaFisica extends GernericModel {
//	private static final long serialVersionUID = 6208168967585694299L;
//
//	@ManyToOne
//	@JoinColumn(name = "fiador", nullable = false)
//	private Fiador fiador;
//
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "pessoaFisica", nullable = false, unique = true)
//	private PessoaFisica pessoaFisica;
//
//	@Column(name = "dataCadastro", nullable = false)
//	private Date dataCadastro;
//
//	public PessoaFisica getPessoaFisica() {
//		if (pessoaFisica == null) {
//			pessoaFisica = new PessoaFisica();
//		}
//		return pessoaFisica;
//	}
//	public void setPessoaFisica(PessoaFisica pessoaFisica) {
//		this.pessoaFisica = pessoaFisica;
//	}
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//	public Fiador getFiador() {
//		return fiador;
//	}
//	public void setFiador(Fiador fiador) {
//		this.fiador = fiador;
//	}
//}
