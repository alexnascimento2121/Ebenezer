package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profissao")
public class Profissao extends GernericModel {
	private static final long serialVersionUID = 1096047280245053198L;

	@Column(name = "descricao", length = 250, nullable = false, unique = true)
	private String descricao;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
