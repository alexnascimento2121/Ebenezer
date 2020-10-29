package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.UnidadeInstituicao;
import br.com.ebenezer.seguranca.HibernateUtil;	

public class UnidadeInstituicaoDAO extends GenericDAO<UnidadeInstituicao> implements Serializable{
	
	private static final long serialVersionUID = 3744610051413439145L;

	@SuppressWarnings("unchecked")
	public List<UnidadeInstituicao> listarIgrejasSuperUsuario(Long idInstituicao){
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "FROM UnidadeInstituicao ui WHERE ui.instituicao.id = :idInstituicao order by ui.nome asc"; 
			Query query = sessao.createQuery(hql);
			query.setParameter("idInstituicao", idInstituicao);
			
			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
