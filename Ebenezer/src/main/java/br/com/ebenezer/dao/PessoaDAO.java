package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.seguranca.HibernateUtil;

public class PessoaDAO extends GenericDAO<Pessoa> implements Serializable {
	private static final long serialVersionUID = 8442759714121722284L;
	
	public Pessoa pesquisaPessoaPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Pessoa pessoa = null;
		try {
			String hql = "from Pessoa p where p.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			pessoa =  (Pessoa) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarPessoaOrdenadoPorNome() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Pessoa p order by p.nomeReceita asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
