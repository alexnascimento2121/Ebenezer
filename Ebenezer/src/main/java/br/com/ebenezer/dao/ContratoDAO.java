//package br.com.ebenezer.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import br.com.ebenezer.model.Contrato;
//import br.com.ebenezer.model.ContratoLocatarioConjunto;
//import br.com.ebenezer.model.ContratoParcela;
//import br.com.ebenezer.seguranca.HibernateUtil;
//
//public class ContratoDAO extends GenericDAO<Contrato> implements Serializable {
//	private static final long serialVersionUID = 2454061399697861116L;
//	
//	@SuppressWarnings("unchecked")
//	public List<Contrato> listarContratoEmAberto() {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//		String hql = "from Contrato c where c.situacao IN ('RASCUNHO', 'EM VINGÊNCIA') order by c.locatario.pessoa.nomeReceita asc";
//		Query query = sessao.createQuery(hql);
//
//		return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<ContratoParcela> listarParcelaPorIDContrato(Long idContrato) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//		String hql = "from ContratoParcela cp where cp.contrato.id = :idContrato order by cp.numero asc";
//		Query query = sessao.createQuery(hql);
//		query.setParameter("idContrato", idContrato);
//
//		return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Contrato> listarContratosPorSituacao(String situacao) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//		String hql = "from Contrato c where c.situacao = :situacao order by c.locatario.pessoa.nomeReceita asc";
//		Query query = sessao.createQuery(hql);
//		query.setParameter("situacao", situacao);
//
//		return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<ContratoLocatarioConjunto> listarLocatarioConjuntoPorIdContrato(Long idContrato) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//
//		String hql = "from ContratoLocatarioConjunto clc where clc.contrato.id = :idContrato "
//				+ "order by clc.pessoa.nomeReceita asc";
//		Query query = sessao.createQuery(hql);
//		query.setParameter("idContrato", idContrato);
//
//		return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//
//}
