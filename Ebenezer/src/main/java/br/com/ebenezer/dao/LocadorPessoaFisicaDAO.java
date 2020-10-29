package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.LocadorPessoaFisica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class LocadorPessoaFisicaDAO extends GenericDAO<LocadorPessoaFisica> implements Serializable {
	private static final long serialVersionUID = 2946388301132699977L;
	
	public LocadorPessoaFisica pesquisaLocadorPFPorLocador(Long idLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		LocadorPessoaFisica pessoaFisica = null;
		try {
			String hql = "from LocadorPessoaFisica lpf where lpf.locador.id = :idLocador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idLocador", idLocador);

			pessoaFisica =  (LocadorPessoaFisica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return pessoaFisica;
	}

}
