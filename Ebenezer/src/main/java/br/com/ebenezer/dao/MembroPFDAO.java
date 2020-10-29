package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;


import br.com.ebenezer.model.Membro;
import br.com.ebenezer.model.MembroPF;
import br.com.ebenezer.seguranca.HibernateUtil;

public class MembroPFDAO extends GenericDAO<Membro> implements Serializable{
	private static final long serialVersionUID = -483186752154738831L;
	
	public MembroPF pesquisaMembroPFPorMembro(Long idMembro) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		MembroPF membroPF = null;
		try {
			String hql = "from MembroPF mpf where mpf.membro.id = :idMembro";
			Query query = sessao.createQuery(hql);
			query.setParameter("idMembro", idMembro);

			membroPF =  (MembroPF) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return membroPF;
	}
}
