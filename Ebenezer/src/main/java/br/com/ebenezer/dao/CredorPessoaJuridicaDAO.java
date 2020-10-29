package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.CredorPessoaJuridica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class CredorPessoaJuridicaDAO extends GenericDAO<CredorPessoaJuridica> implements Serializable {
	private static final long serialVersionUID = 6935852649502119481L;
	
	public CredorPessoaJuridica pesquisaCredorPJPorCredor(Long idCredor) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		CredorPessoaJuridica credorPessoaJuridica = null;
		try {
			String hql = "from CredorPessoaJuridica cpj where cpj.credor.id = :idCredor";
			Query query = sessao.createQuery(hql);
			query.setParameter("idCredor", idCredor);

			credorPessoaJuridica =  (CredorPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return credorPessoaJuridica;
	}

}
