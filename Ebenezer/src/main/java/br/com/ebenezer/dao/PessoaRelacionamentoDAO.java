package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.PessoaRelacionamento;
import br.com.ebenezer.seguranca.HibernateUtil;

public class PessoaRelacionamentoDAO extends GenericDAO<PessoaRelacionamento> implements Serializable {
	private static final long serialVersionUID = -3971605545325922652L;
	
	public PessoaRelacionamento pesquisaRelacionamentoEsquerdaPorIdPessoa(Long idDireita) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaRelacionamento relacionamento = null;
		try {
			String hql = "from PessoaRelacionamento pr where pr.pessoa.id = :idDireita";
			Query query = sessao.createQuery(hql);
			query.setParameter("idDireita", idDireita);

			relacionamento =  (PessoaRelacionamento) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return relacionamento;
	}
	
	public PessoaRelacionamento pesquisaRelacionamentoDireitaPorIdPessoa(Long idEsquerda) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaRelacionamento relacionamento = null;
		try {
			String hql = "from PessoaRelacionamento pr where pr.conjuge.id = :idEsquerda";
			Query query = sessao.createQuery(hql);
			query.setParameter("idEsquerda", idEsquerda);

			relacionamento =  (PessoaRelacionamento) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return relacionamento;
	}
	
	
	
	
	public PessoaRelacionamento pesquisaRelacionamentoEsquerdaParaExcluirPorIdPessoa(Long idPessoa, Long idConjuge) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaRelacionamento relacionamento = null;
		try {
			String hql = "from PessoaRelacionamento pr where pr.pessoa.id = :idPessoa and pr.conjuge.id <> :idConjuge";
			Query query = sessao.createQuery(hql);
			query.setParameter("idPessoa", idPessoa);
			query.setParameter("idConjuge", idConjuge);

			relacionamento =  (PessoaRelacionamento) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return relacionamento;
	}
	
	public PessoaRelacionamento pesquisaRelacionamentoDireitaParaExcluirPorIdPessoa(Long idPessoa, Long idConjuge) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaRelacionamento relacionamento = null;
		try {
			String hql = "from PessoaRelacionamento pr where pr.pessoa.id <> :idPessoa and pr.conjuge.id = :idConjuge";
			Query query = sessao.createQuery(hql);
			query.setParameter("idPessoa", idPessoa);
			query.setParameter("idConjuge", idConjuge);

			relacionamento =  (PessoaRelacionamento) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return relacionamento;
	}

}
