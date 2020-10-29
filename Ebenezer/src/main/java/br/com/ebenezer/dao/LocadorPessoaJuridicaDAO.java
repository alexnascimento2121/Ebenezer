package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.LocadorPessoaJuridica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocadorPessoaJuridicaDAO extends GenericDAO<LocadorPessoaJuridica> implements Serializable {
	private static final long serialVersionUID = 6935852649502119481L;
	
	public LocadorPessoaJuridica pesquisaLocadorPJPorLocador(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocadorPessoaJuridica pessoaJuridica = null;
		try {
			String hql = "from LocadorPessoaJuridica lpj where lpj.locador.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			pessoaJuridica =  (LocadorPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoaJuridica;
	}

}
