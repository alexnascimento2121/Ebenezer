package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.PessoaJuridicaRepresentante;
import br.com.ebenezer.seguranca.HibernateUtil;

public class PessoaJuridicaRepresentanteDAO extends GenericDAO<PessoaJuridicaRepresentante> implements Serializable {
	private static final long serialVersionUID = -6371516373456314332L;
	
	@SuppressWarnings("unchecked")
	public List<PessoaJuridicaRepresentante> listaRepresentantePorIDPJ(Long idPessoaJuridica) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from PessoaJuridicaRepresentante pjr where pjr.pessoaJuridica.id = :idPessoaJuridica "
				+ "order by pjr.pessoaFisica.pessoa.nomeReceita asc";
		Query query = sessao.createQuery(hql);
		query.setParameter("idPessoaJuridica", idPessoaJuridica);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaJuridicaRepresentante> listaRepresentantePorIDPessoa(Long idPessoa) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from PessoaJuridicaRepresentante pjr where pjr.pessoaJuridica.pessoa.id = :idPessoa "
				+ "order by pjr.pessoaFisica.pessoa.nomeReceita asc";
		Query query = sessao.createQuery(hql);
		query.setParameter("idPessoa", idPessoa);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public PessoaJuridicaRepresentante pesquisaRepresentantePorCpfCnpjEPJ(String cpfCnpj, Long idPessoaJuridica) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		PessoaJuridicaRepresentante representante = null;
		try {
			String hql = "from PessoaJuridicaRepresentante pjr where pjr.pessoaFisica.pessoa.cpfCnpj = :cpfCnpj and "
					+ "pjr.pessoaJuridica.id = :idPessoaJuridica";
			Query query = sessao.createQuery(hql);
			query.setParameter("cpfCnpj", cpfCnpj);
			query.setParameter("idPessoaJuridica", idPessoaJuridica);

			representante =  (PessoaJuridicaRepresentante) query.uniqueResult();
		} catch (RuntimeException erro) {
			System.out.println("erro au " + erro.getMessage());
			throw erro;
		} finally {
			sessao.close();
		}
		return representante;
	}

}
