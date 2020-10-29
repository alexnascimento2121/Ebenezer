package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ebenezer.model.GernericModel;

@Entity
@Table(name = "tipounidadeinstituicao")
public class TipoUnidadeInstituicao extends GernericModel{
	private static final long serialVersionUID = -8625319577394771015L;

	@Column(name = "descricao", length = 30, nullable = false)
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
