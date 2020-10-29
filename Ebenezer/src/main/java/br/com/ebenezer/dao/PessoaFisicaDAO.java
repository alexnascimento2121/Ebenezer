package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.PessoaFisica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class PessoaFisicaDAO extends GenericDAO<PessoaFisica> implements Serializable {
	private static final long serialVersionUID = 6430173494295685876L;
	
	public PessoaFisica pesquisaPessoaFisicaPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaFisica pessoaFisica = null;
		try {
			String hql = "from PessoaFisica pf where pf.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			pessoaFisica =  (PessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoaFisica;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> listarPessoaFisicaOrdenadoExcluindoEu(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from PessoaFisica pf where pf.pessoa.cpfCnpj <> :cpfCnpj order by pf.pessoa.nomeReceita asc";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
