package br.com.ebenezer.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.FiadorPessoaJuridica;
import br.com.ebenezer.seguranca.HibernateUtil;

public class FiadorPessoaJuridicaDAO extends GenericDAO<FiadorPessoaJuridica> implements Serializable {
	private static final long serialVersionUID = 6935852649502119481L;
	
	public FiadorPessoaJuridica pesquisaFiadorPJPorFiador(Long idFiador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		FiadorPessoaJuridica fiadorPessoaJuridica = null;
		try {
			String hql = "from FiadorPessoaJuridica lpj where lpj.fiador.id = :idFiador";
			Query query = sessao.createQuery(hql);
			query.setParameter("idFiador", idFiador);

			fiadorPessoaJuridica =  (FiadorPessoaJuridica) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return fiadorPessoaJuridica;
	}

}
