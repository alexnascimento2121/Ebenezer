package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Funcao;
import br.com.ebenezer.seguranca.HibernateUtil;

public class FuncaoDAO extends GenericDAO<Funcao> implements Serializable{
	private static final long serialVersionUID = 565384812203252371L;
	
	@SuppressWarnings("unchecked")
	public List<Funcao> listaFuncaoOrdenada() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

		String hql = "from Funcao f order by f.descricao asc";
		Query query = sessao.createQuery(hql);

		return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
