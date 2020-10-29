package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.LocatarioPessoaFisica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocatarioPessoaFisicaDAO extends GenericDAO<LocatarioPessoaFisica> implements Serializable {
	private static final long serialVersionUID = 3896559483510840005L;

	public LocatarioPessoaFisica pesquisaLocatarioPFPorLocatario(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocatarioPessoaFisica locatarioPF = null;
		try {
			String hql = "from LocatarioPessoaFisica lpf where lpf.locatario.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			locatarioPF =  (LocatarioPessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locatarioPF;
	}
}
