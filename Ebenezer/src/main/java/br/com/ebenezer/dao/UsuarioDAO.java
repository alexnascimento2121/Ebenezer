//package br.com.ebenezer.dao;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import br.com.ebenezer.model.Usuario;
//import br.com.ebenezer.seguranca.HibernateUtil;
//
//public class UsuarioDAO extends GenericDAO<Usuario> {
//	
//	public Usuario autenticar(String usuario, String senha) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		Usuario usuarioLogin = null;
//		senha = senha.toUpperCase();
//		try {
//			
//			String hql = "from Usuario u where u.login = :login and u.senha = :senha";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("login", usuario);
//			query.setParameter("senha", senha.toUpperCase());
//
//			usuarioLogin =  (Usuario) query.uniqueResult();
//			
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return usuarioLogin;
//	}
//	
//	
//	public Usuario buscarPorID(Long id) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		Usuario usuario = null;
//
//		try {
//			String hql = "from Usuario u where u.id = :id";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("id", id);
//
//			usuario =  (Usuario) query.uniqueResult();
//			
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return usuario;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Usuario> usuarioAtualizaSenha(String login) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//		String hql = "from Usuario u where u.login = :login";
//		Query query = sessao.createQuery(hql);
//		query.setParameter("login", login);
//
//		return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//	
//}
