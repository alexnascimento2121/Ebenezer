//package br.com.ebenezer.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import br.com.ebenezer.model.ReciboAvulso;
//import br.com.ebenezer.seguranca.HibernateUtil;
//
//public class ReciboAvulsoDAO extends GenericDAO<ReciboAvulso> implements Serializable {
//	private static final long serialVersionUID = -5117092168603333997L;
//
//	@SuppressWarnings("unchecked")
//	public List<ReciboAvulso> listarReciboAvulso() {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//			String hql = "from ReciboAvulso ra order by ra.id desc";
//			Query query = sessao.createQuery(hql);
//
//			return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//}
