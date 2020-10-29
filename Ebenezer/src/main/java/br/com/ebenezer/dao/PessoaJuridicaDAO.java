package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.PessoaJuridica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class PessoaJuridicaDAO extends GenericDAO<PessoaJuridica> implements Serializable {
	private static final long serialVersionUID = 8433822665203332306L;
	
	public PessoaJuridica pesquisaPessoaJuridicaPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaJuridica pessoaJuridica = null;
		try {
			String hql = "from PessoaJuridica pj where pj.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			pessoaJuridica =  (PessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoaJuridica;
	}

}
