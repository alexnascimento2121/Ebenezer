package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Locador;
import br.com.ebenezer.model.LocadorPessoaFisica;
import br.com.ebenezer.model.LocadorPessoaJuridica;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocadorDAO extends GenericDAO<Locador> implements Serializable {
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

	public Locador pesquisaLocadorPorCpfCnpj(String cpfCnpj) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Locador locador = null;
		try {
			String hql = "from Locador l where l.pessoa.cpfCnpj = :cpfCnpj";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);

			locador = (Locador) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locador;
	}

	public LocadorPessoaJuridica pesquisaLocadorPJPorIdLocado(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocadorPessoaJuridica locadorPj = null;
		try {
			String hql = "from LocadorPessoaJuridica lpj where lpj.locador.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			locadorPj = (LocadorPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locadorPj;
	}

	public LocadorPessoaFisica pesquisaLocadorPFPorIdLocado(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocadorPessoaFisica locadorPF = null;
		try {
			String hql = "from LocadorPessoaFisica lpf where lpf.locador.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			locadorPF = (LocadorPessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return locadorPF;
	}

	@SuppressWarnings("unchecked")
	public List<Locador> listarLocadorOrdenadoPorNome() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Locador l order by l.pessoa.nomeReceita asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
