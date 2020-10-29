package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Profissao;
import br.com.ebenezer.seguranca.HibernateUtil;

public class ProfissaoDAO extends GenericDAO<Profissao> implements Serializable{
	private static final long serialVersionUID = 2887258137138198851L;
	
	@SuppressWarnings("unchecked")
	public List<Profissao> listaProfissaoOrdenadoPorNone() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from Profissao p order by p.descricao asc";
		Query query = sessao.createQuery(hql);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
