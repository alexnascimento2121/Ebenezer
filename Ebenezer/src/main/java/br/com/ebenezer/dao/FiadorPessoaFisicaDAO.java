//package br.com.ebenezer.dao;
//
//import java.io.Serializable;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import br.com.ebenezer.model.FiadorPessoaFisica;
//import br.com.ebenezer.seguranca.HibernateUtil;
//
//public class FiadorPessoaFisicaDAO extends GenericDAO<FiadorPessoaFisica> implements Serializable {
//	private static final long serialVersionUID = 2946388301132699977L;
//	
//	public FiadorPessoaFisica pesquisaFiadorPFPorFiador(Long idFiador) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		FiadorPessoaFisica fiadorPessoaFisica = null;
//		try {
//			String hql = "from FiadorPessoaFisica fpf where fpf.fiador.id = :idFiador";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("idFiador", idFiador);
//
//			fiadorPessoaFisica =  (FiadorPessoaFisica) query.uniqueResult();
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return fiadorPessoaFisica;
//	}
//
//}
