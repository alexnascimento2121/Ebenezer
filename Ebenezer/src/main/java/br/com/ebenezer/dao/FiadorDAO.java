//package br.com.ebenezer.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import br.com.ebenezer.model.Fiador;
//import br.com.ebenezer.model.FiadorPessoaFisica;
//import br.com.ebenezer.model.FiadorPessoaJuridica;
//import br.com.ebenezer.model.Pessoa;
//import br.com.ebenezer.seguranca.HibernateUtil;
//
//public class FiadorDAO extends GenericDAO<Fiador> implements Serializable {
//	private static final long serialVersionUID = -6629080022269918139L;
//
//	public Pessoa pesquisaPessoaPorCpfCnpj(String cpfCnpj) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		Pessoa pessoa = null;
//		try {
//			String hql = "from Pessoa p where p.cpfCnpj = :cpfCnpj";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("cpfCnpj", cpfCnpj);
//
//			pessoa = (Pessoa) query.uniqueResult();
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return pessoa;
//	}
//
//	public Fiador pesquisaFiadorPorCpfCnpj(String cpfCnpj) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		Fiador fiador = null;
//		try {
//			String hql = "from Fiador f where f.pessoa.cpfCnpj = :cpfCnpj";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("cpfCnpj", cpfCnpj);
//
//			fiador = (Fiador) query.uniqueResult();
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return fiador;
//	}
//
//	public FiadorPessoaJuridica pesquisaFiadorPJPorIdLocado(Long idFiador) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		FiadorPessoaJuridica fiadorPessoaJuridica = null;
//		try {
//			String hql = "from FiadorPessoaJuridica fpj where fpj.fiador.id = :idFiador";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("idFiador", idFiador);
//
//			fiadorPessoaJuridica = (FiadorPessoaJuridica) query.uniqueResult();
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return fiadorPessoaJuridica;
//	}
//
//	public FiadorPessoaFisica pesquisaFiadorPFPorIdLocado(Long idFiador) {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		FiadorPessoaFisica fiadorPessoaFisica = null;
//		try {
//			String hql = "from FiadorPessoaFisica fpf where fpf.fiador.id = :idFiador";
//			Query query = sessao.createQuery(hql);
//			query.setParameter("idFiador", idFiador);
//
//			fiadorPessoaFisica = (FiadorPessoaFisica) query.uniqueResult();
//		} catch (RuntimeException erro) {
//			System.out.println("erro au " + erro.getMessage());
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//		return fiadorPessoaFisica;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Fiador> listarFiadorOrdenadoPorNome() {
//		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
//		try {
//			String hql = "from Fiador f order by f.pessoa.nomeReceita asc";
//			Query query = sessao.createQuery(hql);
//
//			return query.list();
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//	}
//
//}
