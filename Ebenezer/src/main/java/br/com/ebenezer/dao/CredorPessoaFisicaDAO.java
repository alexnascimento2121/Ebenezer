package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.CredorPessoaFisica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class CredorPessoaFisicaDAO extends GenericDAO<CredorPessoaFisica> implements Serializable {
	private static final long serialVersionUID = 2946388301132699977L;
	
	public CredorPessoaFisica pesquisaCredorPFPorCredor(Long idCredor) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		CredorPessoaFisica credorPessoaFisica = null;
		try {
			String hql = "from CredorPessoaFisica cpf where cpf.credor.id = :idCredor";
			Query query = sessao.createQuery(hql);
			query.setParameter("idCredor", idCredor);

			credorPessoaFisica =  (CredorPessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return credorPessoaFisica;
	}

}
