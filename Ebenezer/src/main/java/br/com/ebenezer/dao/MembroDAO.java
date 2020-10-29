package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import br.com.ebenezer.model.Membro;
import br.com.ebenezer.model.MembroPF;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.seguranca.HibernateUtil;

public class MembroDAO extends GenericDAO<Membro> implements Serializable{
	private static final long serialVersionUID = 4322974181891803741L;
	
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

	public Membro pesquisaMembroPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Membro Membro = null;
		try {
			String hql = "from Membro m where m.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			Membro = (Membro) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return Membro;
	}
	
	public MembroPF pesquisaMembroPFPorIdMembro(Long idMembro) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		MembroPF membroPF = null;
		try {
			String hql = "from MembroPF mpf where mpf.membro.id = :idMembro";
			Query query = sessao.createQuery(hql);
			query.setParameter("idMembro", idMembro);

			membroPF = (MembroPF) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return membroPF;
	}

	@SuppressWarnings("unchecked")
	public List<Membro> listarMembroOrdenadoPorNome() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Membro m order by m.pessoa.nomeReceita asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
