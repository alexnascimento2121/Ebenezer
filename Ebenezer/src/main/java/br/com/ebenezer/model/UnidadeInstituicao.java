package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ebenezer.model.GernericModel;

@Entity
@Table(name = "unidadeinstituicao")
public class UnidadeInstituicao extends GernericModel{
	private static final long serialVersionUID = -511098201559066451L;
	
	@Column(name = "nome", length = 120, nullable = false)
	private String nome;
	
	@Column(name = "apelido", length = 50)
	private String apelido;	
	
	@Column(name = "cnpj", length = 18)
	private String cnpj;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "tipoUnidadeInstituicao", nullable = false)
	private TipoUnidadeInstituicao tipoUnidadeInstituicao;
	
	@ManyToOne
	@JoinColumn(name = "instituicao")
	private Instituicao instituicao;	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public TipoUnidadeInstituicao getTipoUnidadeInstituicao() {
		return tipoUnidadeInstituicao;
	}
	public void setTipoUnidadeInstituicao(TipoUnidadeInstituicao tipoUnidadeInstituicao) {
		this.tipoUnidadeInstituicao = tipoUnidadeInstituicao;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
