package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ebenezer.model.GernericModel;

@Entity
@Table(name = "instituicao")
public class Instituicao extends GernericModel{
	private static final long serialVersionUID = 1608817763317733077L;
	
	@Column(name = "descricao", length = 120, nullable = false)
	private String descricao;
	
	@Column(name = "codigo", nullable = false)
	private int codigo;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "tipounidadeinstituicao")
	private TipoUnidadeInstituicao tipounidadeinstituicao;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public TipoUnidadeInstituicao getTipounidadeinstituicao() {
		return tipounidadeinstituicao;
	}
	public void setTipounidadeinstituicao(TipoUnidadeInstituicao tipounidadeinstituicao) {
		this.tipounidadeinstituicao = tipounidadeinstituicao;
	}
}
