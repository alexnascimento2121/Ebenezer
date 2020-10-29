package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Credor;
import br.com.ebenezer.model.CredorPessoaFisica;
import br.com.ebenezer.model.CredorPessoaJuridica;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.seguranca.HibernateUtil;

public class CredorDAO extends GenericDAO<Credor> implements Serializable {
	private static final long serialVersionUID = -6629080022269918139L;

	public Pessoa pesquisaPessoaPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Pessoa pessoa = null;
		try {
			String hql = "from Pessoa p where p.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			pessoa = (Pessoa) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoa;
	}

	public Credor pesquisaCredorPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Credor Credor = null;
		try {
			String hql = "from Credor f where f.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			Credor = (Credor) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return Credor;
	}

	public CredorPessoaJuridica pesquisaCredorPJPorIdCredor(Long idCredor) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		CredorPessoaJuridica credorPessoaJuridica = null;
		try {
			String hql = "from CredorPessoaJuridica cpj where cpj.credor.id = :idCredor";
			Query query = sessao.createQuery(hql);
			query.setParameter("idCredor", idCredor);

			credorPessoaJuridica = (CredorPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return credorPessoaJuridica;
	}

	public CredorPessoaFisica pesquisaCredorPFPorIdCredor(Long idCredor) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		CredorPessoaFisica credorPessoaFisica = null;
		try {
			String hql = "from CredorPessoaFisica cpf where cpf.credor.id = :idCredor";
			Query query = sessao.createQuery(hql);
			query.setParameter("idCredor", idCredor);

			credorPessoaFisica = (CredorPessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return credorPessoaFisica;
	}

	@SuppressWarnings("unchecked")
	public List<Credor> listarCredorOrdenadoPorNome() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Credor f order by f.pessoa.nomeReceita asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
