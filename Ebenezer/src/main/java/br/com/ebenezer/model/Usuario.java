package br.com.ebenezer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ebenezer.model.enums.EnumSituacao;

@Entity
@Table(name = "usuario")
public class Usuario extends GernericModel {
	private static final long serialVersionUID = 5644709873539081109L;
	
	@ManyToOne
	@JoinColumn(name = "funcionario", nullable = false)
	private Funcionario funcionario;

	@Column(name = "login", length = 20, unique = true, nullable = false)
	private String login;

	@Column(name = "senha", length = 45, nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", length = 10)
	private EnumSituacao situacao;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataCadastro;

	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	public EnumSituacao getSituacao() {
		return situacao;
	}
	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
