package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Cnae;
import br.com.ebenezer.seguranca.HibernateUtil;

public class CnaeDAO extends GenericDAO<Cnae> implements Serializable {
	private static final long serialVersionUID = -780196323251247892L;
	
	@SuppressWarnings("unchecked")
	public List<Cnae> listaCnaeOrdenadoPorNone() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from Cnae c order by c.descricao asc";
		Query query = sessao.createQuery(hql);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
