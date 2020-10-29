package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.UsuarioAdministracao;
import br.com.ebenezer.seguranca.HibernateUtil;

public class UsuarioAdministracaoDAO extends GenericDAO<UsuarioAdministracao> implements Serializable{

	private static final long serialVersionUID = 3741293076322903421L;

	public UsuarioAdministracao autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		UsuarioAdministracao usuarioLogin = null;
		try {
			String hql = "from UsuarioAdministracao ua where ua.login = :login and ua.senha = :senha";
			Query query = sessao.createQuery(hql);
			query.setParameter("login", login);
			query.setParameter("senha", senha);

			usuarioLogin =  (UsuarioAdministracao) query.uniqueResult();
			
		} catch (RuntimeException erro) {
			System.out.println("erro " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return usuarioLogin;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioAdministracao> listarUsuarioAdministracao() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from UsuarioAdministracao ua order by ua.id asc";
		Query query = sessao.createQuery(hql);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
