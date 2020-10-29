package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ebenezer.model.GernericModel;

@Entity
@Table(name = "usuarioadministracao")
public class UsuarioAdministracao extends GernericModel {
	private static final long serialVersionUID = -3525490112003444979L;
	
	@Column(name = "nomeFuncionario", length = 120, nullable = false)
	private String nomeFuncionario;

	@Column(name = "login", length = 20, unique = true, nullable = false)
	private String login;

	@Column(name = "senha", length = 35, nullable = false)
	private String senha;
	
	@Column(name = "situacao", length = 10, nullable = false)
	private String situacao;
	
	@Column(name = "tipoAcesso", length = 20)
	private String tipoAcesso;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
