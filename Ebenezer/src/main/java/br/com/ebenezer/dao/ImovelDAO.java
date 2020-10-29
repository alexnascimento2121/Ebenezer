package br.com.ebenezer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.ebenezer.model.Imovel;
import br.com.ebenezer.seguranca.HibernateUtil;

public class ImovelDAO extends GenericDAO<Imovel> implements Serializable {
	private static final long serialVersionUID = 4770396780138658428L;
	
	@SuppressWarnings("unchecked")
	public List<Imovel> listarImovelOrdenadoPorDescricao() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Imovel i order by i.descricao asc";
			Query query = sessao.createQuery(hql);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Imovel> listarImovelOrdenadoPorDono(Long idDonoLocador) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			String hql = "from Imovel i where i.dono.id = :idDonoLocador order by i.descricao asc";
			Query query = sessao.createQuery(hql);
			query.setParameter("idDonoLocador", idDonoLocador);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
