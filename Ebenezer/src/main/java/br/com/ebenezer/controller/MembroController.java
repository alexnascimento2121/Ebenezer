package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ebenezer.dao.CnaeDAO;
import br.com.ebenezer.dao.MembroDAO;
import br.com.ebenezer.dao.MembroPFDAO;
import br.com.ebenezer.dao.PessoaDAO;
import br.com.ebenezer.dao.PessoaFisicaDAO;
import br.com.ebenezer.dao.PessoaJuridicaRepresentanteDAO;
import br.com.ebenezer.dao.PessoaRelacionamentoDAO;
import br.com.ebenezer.dao.ProfissaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.Cnae;
import br.com.ebenezer.model.CredorPessoaFisica;
import br.com.ebenezer.model.CredorPessoaJuridica;
import br.com.ebenezer.model.Membro;
import br.com.ebenezer.model.MembroPF;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.model.PessoaFisica;
import br.com.ebenezer.model.PessoaJuridicaRepresentante;
import br.com.ebenezer.model.PessoaRelacionamento;
import br.com.ebenezer.model.Profissao;
import br.com.ebenezer.model.enums.EnumEstadoCivil;
import br.com.ebenezer.model.enums.EnumTipoDocumento;
import br.com.ebenezer.util.ConverteCaixaAlta;

@ManagedBean
@ViewScoped
public class MembroController implements Serializable{
	private static final long serialVersionUID = 7519692890846420458L;
	
	private Membro membro;
	private List<Membro> listaMembro;
	private MembroDAO membroDAO = new MembroDAO();
	
	private MembroPF membroPF;
	private MembroPFDAO membroPFDAO = new MembroPFDAO();
	
	private List<EnumTipoDocumento> listaEnumTipoDocumento;
	private List<EnumEstadoCivil> listaEnumEstadoCivil;
	
	private Pessoa pessoa;
	private PessoaDAO pessoaDAO = new PessoaDAO();
	
	private PessoaRelacionamento relacionamento;
	private PessoaRelacionamentoDAO relacionamentoDAO = new PessoaRelacionamentoDAO();
	
	private PessoaFisica conjuge;
	private List<PessoaFisica> listaConjuge;
	private PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
	
	private PessoaJuridicaRepresentante representante;
	private List<PessoaJuridicaRepresentante> listaRepresentante;
	private PessoaJuridicaRepresentanteDAO representanteDAO = new PessoaJuridicaRepresentanteDAO();
	
	private Cnae cnae;
	private List<Cnae> listaCnae;
	private CnaeDAO cnaeDAO = new CnaeDAO();

	private Profissao profissao;
	private List<Profissao> listaProfissao;
	private ProfissaoDAO profissaoDAO = new ProfissaoDAO();
	
	private String cpfTemporario;
	private RelacionamentoPessoaController relacionamentoController = new RelacionamentoPessoaController();
	
	@PostConstruct
	public void listar() {
		try {
			listaMembro = membroDAO.listar();
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		membro = new Membro();
		membro.setDataCadastro(new Date());
	}
	
	public void retornaDadosRelacionamento(MembroPF MembroPF) {
		if (MembroPF.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
			listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(membro.getPessoa().getCpfCnpj());
			
			relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(membroPF.getPessoaFisica().getId());
			if (relacionamento == null) {
				this.novoRelacionamento();
				relacionamento.setPessoa(membroPF.getPessoaFisica());
			}
		}
	}
	
	public void novoRelacionamento() {
		relacionamento = new PessoaRelacionamento();
		relacionamento.setDataCadastro(new Date());
		this.verificaRelacionamento();
	}
	
	public void novoConjuge() {
		conjuge = new PessoaFisica();
		conjuge.getPessoa().setDataCadastro(new Date());
	}
	
	public void pesquisaConjuge() {
		cpfTemporario = conjuge.getPessoa().getCpfCnpj();
		pessoa = membroDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
		
		if (pessoa != null) {
			conjuge = pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario);
			relacionamento = relacionamentoDAO.pesquisaRelacionamentoDireitaPorIdPessoa(conjuge.getPessoa().getId());
			if (relacionamento == null) {
				this.novoRelacionamento();
			}
		} else {
			this.novoRelacionamento();
		}
	}
	
	public void verificaRelacionamento() {
		if (membro.getPessoa().getCpfCnpj().length() == 18) {
			relacionamento.setPessoa(representante.getPessoaFisica());
		} else if (membro.getPessoa().getCpfCnpj().length() == 14) {
			relacionamento.setPessoa(membroPF.getPessoaFisica());
		}
	}
	
//	public void pesquisaCpfCnpj() {
//		try {
//			cpfTemporario = membro.getPessoa().getCpfCnpj();
//			
//			if (membro.getPessoa().getCpfCnpj().length() == 14) {
//				listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//				listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//
//			} else if (membro.getPessoa().getCpfCnpj().length() == 18) {
//				listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//				listaRepresentante = new ArrayList<>();
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//				return;
//			}
//
//			membro = membroDAO.pesquisaMembroPorCpfCnpj(cpfTemporario);
//			pessoa = membroDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
//
//			if (membro == null) {
//				this.novo();
//				credor.getPessoa().setCpfCnpj(cpfTemporario);
//			} else {
//				credor.setPessoa(ConverteCaixaAlta.convertePessoa(credor.getPessoa()));
//				if (credor.getPessoa().getCpfCnpj().length() == 14) {
//				}
//			}
//			
//			if (pessoa != null) {
//				credor.setPessoa(pessoa);
//			}
//			
//			if (credor.getId() == null && credor.getPessoa().getCpfCnpj().length() == 14) {
//				credorPF = new CredorPessoaFisica();
//				credorPF.setDataCadastro(new Date());
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				if (pessoa != null) {
//					credorPF.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
//					this.retornaDadosRelacionamento(credorPF);
//				}
//				return;
//			} else if (credor.getId() != null && credor.getPessoa().getCpfCnpj().length() == 14) {
//				credorPF = credorDAO.pesquisaCredorPFPorIdCredor(credor.getId());
//				this.retornaDadosRelacionamento(credorPF);
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				return;
//				
//			} else if (credor.getId() == null && credor.getPessoa().getCpfCnpj().length() == 18) {
//				credorPF = null;
//				credorPJ = new CredorPessoaJuridica();
//				credorPJ.setDataCadastro(new Date());
//				if (pessoa != null) {
//					credorPJ.setPessoaJuridica(pessoaJuridicaDAO.pesquisaPessoaJuridicaPorCpfCnpj(cpfTemporario));
//					listaRepresentante = representanteDAO.listaRepresentantePorIDPessoa(pessoa.getId());
//				}
//				return;
//			} else if (credor.getId() != null && credor.getPessoa().getCpfCnpj().length() == 18) {
//				credorPJ = credorDAO.pesquisaCredorPJPorIdCredor(credor.getId());
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(credorPJ.getPessoaJuridica().getId());
//				return;
//			}
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}

}
