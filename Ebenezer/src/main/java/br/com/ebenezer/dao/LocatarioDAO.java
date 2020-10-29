package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Locatario;
import br.com.ebenezer.model.LocatarioPessoaFisica;
import br.com.ebenezer.model.LocatarioPessoaJuridica;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocatarioDAO extends GenericDAO<Locatario> implements Serializable {
	private static final long serialVersionUID = -291747265170525620L;

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
	
	public Locatario pesquisaLocatarioPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Locatario locatario = null;
		try {
			String hql = "from Locatario l where l.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			locatario =  (Locatario) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locatario;
	}
	
	public LocatarioPessoaJuridica pesquisaLocatarioPJPorIdLocatario(Long idLocador) {
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
	
	public LocatarioPessoaFisica pesquisaLocatarioPFPorIdLocatario(Long idLocador) {
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
	
	@SuppressWarnings("unchecked")
	public List<Locatario> listarLocatarioOrdenadoPorNome() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Locatario l order by l.pessoa.nomeReceita asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
