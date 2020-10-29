package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.LocatarioPessoaJuridica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocatarioPessoaJuridicaDAO extends GenericDAO<LocatarioPessoaJuridica> implements Serializable {
	private static final long serialVersionUID = 6106276737286622649L;
	
	public LocatarioPessoaJuridica pesquisaLocatarioPJPorLocatario(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocatarioPessoaJuridica locatarioPj = null;
		try {
			String hql = "from LocatarioPessoaJuridica lpj where lpj.locatario.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			locatarioPj =  (LocatarioPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locatarioPj;
	}

}
