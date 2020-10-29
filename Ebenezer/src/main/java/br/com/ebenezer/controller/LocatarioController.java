//package br.com.ebenezer.controller;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.event.ActionEvent;
//
//import org.primefaces.PrimeFaces;
//
//import br.com.ebenezer.dao.CnaeDAO;
//import br.com.ebenezer.dao.LocatarioDAO;
//import br.com.ebenezer.dao.LocatarioPessoaFisicaDAO;
//import br.com.ebenezer.dao.LocatarioPessoaJuridicaDAO;
//import br.com.ebenezer.dao.PessoaDAO;
//import br.com.ebenezer.dao.PessoaFisicaDAO;
//import br.com.ebenezer.dao.PessoaJuridicaDAO;
//import br.com.ebenezer.dao.PessoaJuridicaRepresentanteDAO;
//import br.com.ebenezer.dao.PessoaRelacionamentoDAO;
//import br.com.ebenezer.dao.ProfissaoDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Cnae;
//import br.com.ebenezer.model.Locatario;
//import br.com.ebenezer.model.LocatarioPessoaFisica;
//import br.com.ebenezer.model.LocatarioPessoaJuridica;
//import br.com.ebenezer.model.Pessoa;
//import br.com.ebenezer.model.Profissao;
//import br.com.ebenezer.model.enums.EnumEstadoCivil;
//import br.com.ebenezer.model.enums.EnumTipoDocumento;
//import br.com.ebenezer.model.nova.PessoaFisica;
//import br.com.ebenezer.model.nova.PessoaJuridicaRepresentante;
//import br.com.ebenezer.model.nova.PessoaRelacionamento;
//import br.com.ebenezer.util.ConverteCaixaAlta;
//import br.com.ebenezer.webservice.ConsultaCEP;
//
//@ManagedBean
//@ViewScoped
//public class LocatarioController implements Serializable {
//	private static final long serialVersionUID = -8304268184224896552L;
//
//	private Locatario locatario;
//	private List<Locatario> listaLocatario;
//	private List<Locatario> listaLocatarioFiltrados;
//	private LocatarioDAO locatarioDAO = new LocatarioDAO();
//
//	private List<EnumTipoDocumento> listaEnumTipoDocumento;
//	private List<EnumEstadoCivil> listaEnumEstadoCivil;
//
//	private LocatarioPessoaFisica locatarioPF;
//	private LocatarioPessoaFisicaDAO locatarioPFDAO = new LocatarioPessoaFisicaDAO();
//	
//	private PessoaRelacionamento relacionamento;
//	private PessoaRelacionamentoDAO relacionamentoDAO = new PessoaRelacionamentoDAO(); 
//
//	private LocatarioPessoaJuridica locatarioPJ;
//	private LocatarioPessoaJuridicaDAO locatarioPJDAO = new LocatarioPessoaJuridicaDAO();
//
//	private Pessoa pessoa;
//	private PessoaDAO pessoaDAO = new PessoaDAO();
//
//	private PessoaFisica conjuge;
//	private List<PessoaFisica> listaConjuge;
//	private PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
//	
//	private PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
//
//	private Cnae cnae;
//	private List<Cnae> listaCnae;
//	private CnaeDAO cnaeDAO = new CnaeDAO();
//
//	private Profissao profissao;
//	private List<Profissao> listaProfissao;
//	private ProfissaoDAO profissaoDAO = new ProfissaoDAO();
//
//	private PessoaJuridicaRepresentante representante;
//	private List<PessoaJuridicaRepresentante> listaRepresentante;
//	private PessoaJuridicaRepresentanteDAO representanteDAO = new PessoaJuridicaRepresentanteDAO();
//	
//	private String cpfTemporario;
//	private RelacionamentoPessoaController relacionamentoController = new RelacionamentoPessoaController();
//
//	@PostConstruct
//	public void listar() {
//		try {
//			listaLocatario = locatarioDAO.listar();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		locatario = new Locatario();
//		locatario.setDataCadastro(new Date());
//	}
//	
//	public void retornaDadosRelacionamento(LocatarioPessoaFisica locatarioPessoaFisica) {
//		if (locatarioPessoaFisica.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//			listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(locatario.getPessoa().getCpfCnpj());
//			
//			relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(locatarioPessoaFisica.getPessoaFisica().getId());
//			if (relacionamento == null) {
//				this.novoRelacionamento();
//				relacionamento.setPessoa(locatarioPessoaFisica.getPessoaFisica());
//			}
//		}
//	}
//
//	public void pesquisaCpfCnpj() {
//		try {
//			cpfTemporario = locatario.getPessoa().getCpfCnpj();
//			
//			if (locatario.getPessoa().getCpfCnpj().length() == 14) {
//				listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//				listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//
//			} else if (locatario.getPessoa().getCpfCnpj().length() == 18) {
//				listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//				listaRepresentante = new ArrayList<>();
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//				return;
//			}
//
//			locatario = locatarioDAO.pesquisaLocatarioPorCpfCnpj(cpfTemporario);
//			pessoa = locatarioDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
//
//			if (locatario == null) {
//				this.novo();
//				locatario.getPessoa().setCpfCnpj(cpfTemporario);
//			} else {
//				locatario.setPessoa(ConverteCaixaAlta.convertePessoa(locatario.getPessoa()));
//				if (locatario.getPessoa().getCpfCnpj().length() == 14) {
//				}
//			}
//			
//			if (pessoa != null) {
//				locatario.setPessoa(pessoa);
//			}
//			
//			if (locatario.getId() == null && locatario.getPessoa().getCpfCnpj().length() == 14) {
//				locatarioPF = new LocatarioPessoaFisica();
//				locatarioPF.setDataCadastro(new Date());
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				if (pessoa != null) {
//					locatarioPF.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
//					this.retornaDadosRelacionamento(locatarioPF);
//				}
//				return;
//			} else if (locatario.getId() != null && locatario.getPessoa().getCpfCnpj().length() == 14) {
//				locatarioPF = locatarioDAO.pesquisaLocatarioPFPorIdLocatario(locatario.getId());
//				this.retornaDadosRelacionamento(locatarioPF);
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				return;
//				
//			} else if (locatario.getId() == null && locatario.getPessoa().getCpfCnpj().length() == 18) {
//				locatarioPF = null;
//				locatarioPJ = new LocatarioPessoaJuridica();
//				locatarioPJ.setDataCadastro(new Date());
//				if (pessoa != null) {
//					locatarioPJ.setPessoaJuridica(pessoaJuridicaDAO.pesquisaPessoaJuridicaPorCpfCnpj(cpfTemporario));
//					listaRepresentante = representanteDAO.listaRepresentantePorIDPessoa(pessoa.getId());
//				}
//				return;
//			} else if (locatario.getId() != null && locatario.getPessoa().getCpfCnpj().length() == 18) {
//				locatarioPJ = locatarioDAO.pesquisaLocatarioPJPorIdLocatario(locatario.getId());
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(locatarioPJ.getPessoaJuridica().getId());
//				return;
//			}
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void verificaEstadoCivil(int opcao) {
//		if (locatario.getPessoa().getCpfCnpj().length() == 18) {
//			if(representante.getPessoaFisica().getEstadoCivil() == null) {
//				return;
//			}
//		}
//		
//		if (opcao == 1) {
//			if (representante.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(representante.getPessoaFisica().getPessoa().getCpfCnpj());
//				
//				relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(representante.getPessoaFisica().getId());
//				if (relacionamento == null) {
//					this.novoRelacionamento();
//				}
//				relacionamento.setPessoa(representante.getPessoaFisica());
//			}
//		} else if (opcao == 2) {
//			this.retornaDadosRelacionamento(locatarioPF);
//		}
//	}
//	
//	public void novoRelacionamento() {
//		relacionamento = new PessoaRelacionamento();
//		relacionamento.setDataCadastro(new Date());
//		this.verificaRelacionamento();
//	}
//	
//	public void novoConjuge() {
//		conjuge = new PessoaFisica();
//		conjuge.getPessoa().setDataCadastro(new Date());
//	}
//	
//	public void pesquisaConjuge() {
//		cpfTemporario = conjuge.getPessoa().getCpfCnpj();
//		pessoa = locatarioDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
//		
//		if (pessoa != null) {
//			conjuge = pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario);
//			relacionamento = relacionamentoDAO.pesquisaRelacionamentoDireitaPorIdPessoa(conjuge.getPessoa().getId());
//			if (relacionamento == null) {
//				this.novoRelacionamento();
//			}
//		} else {
//			this.novoRelacionamento();
//		}
//	}
//	
//	public void salvarConjuge() {
//		try {
//			pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(conjuge.getPessoa()));
//			conjuge.setPessoa(pessoaDAO.getRetornaId());
//			
//			pessoaFisicaDAO.merge(ConverteCaixaAlta.convertePessoaFisica(conjuge));
//			
//			relacionamento.setConjuge(pessoaFisicaDAO.getRetornaId());
//			this.verificaRelacionamento();
//			PrimeFaces.current().executeScript("PF('dlgConjuge').hide();");
//			
//			if (locatario.getPessoa().getCpfCnpj().length() == 18) {
//				PrimeFaces.current().ajax().update("FormCadRepresentate");
//				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(representante.getPessoaFisica().getPessoa().getCpfCnpj());
//				
//			} else if (locatario.getPessoa().getCpfCnpj().length() == 14) {
//				PrimeFaces.current().ajax().update("FormCadLocatario:pnlConjugeMain");
//				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(locatarioPF.getPessoaFisica().getPessoa().getCpfCnpj());
//			}
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novaProfissao() {
//		profissao = new Profissao();
//		profissao.setDataCadastro(new Date());
//	}
//
//	public void salvarProfissao() {
//		try {
//			profissao.setDescricao(profissao.getDescricao().toUpperCase());
//			profissaoDAO.merge(profissao);
//			listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//			
//			if (locatarioPF != null) {
//				locatarioPF.getPessoaFisica().setProfissao(profissaoDAO.getRetornaId());
//			}
//
//			if (representante != null) {
//				representante.getPessoaFisica().setProfissao(profissaoDAO.getRetornaId());
//			}
//
//			PrimeFaces.current().executeScript("PF('dlgProfissao').hide();");
//		} catch (RuntimeException erro) {
//			if (erro.getMessage().equals("could not execute statement")) {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Profissão já Cadastrada!");
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//				erro.printStackTrace();
//			}
//		}
//	}
//
//	public void novoCnae() {
//		cnae = new Cnae();
//		cnae.setDataCadastro(new Date());
//	}
//
//	public void salvarCnae() {
//		try {
//			cnae.setDescricao(cnae.getDescricao().toUpperCase());
//			cnaeDAO.merge(cnae);
//			listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//			locatarioPJ.getPessoaJuridica().setCnae(cnaeDAO.getRetornaId());
//
//			PrimeFaces.current().executeScript("PF('dlgCnae').hide();");
//		} catch (RuntimeException erro) {
//			if (erro.getMessage().equals("could not execute statement")) {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Cnae já Cadastrado!");
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//				erro.printStackTrace();
//			}
//		}
//	}
//	
//	public void verificaRelacionamento() {
//		if (locatario.getPessoa().getCpfCnpj().length() == 18) {
//			relacionamento.setPessoa(representante.getPessoaFisica());
//		} else if (locatario.getPessoa().getCpfCnpj().length() == 14) {
//			relacionamento.setPessoa(locatarioPF.getPessoaFisica());
//		}
//	}
//
//	public void novoRepresentante() {
//		representante = new PessoaJuridicaRepresentante();
//		representante.setDataCadastro(new Date());
//		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//		listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//	}
//
//	public void novoPessoaRepresentante(String cpf) {
//		representante = new PessoaJuridicaRepresentante();
//		representante.setDataCadastro(new Date());
//		representante.getPessoaFisica().setPessoa(new Pessoa());
//		representante.getPessoaFisica().getPessoa().setDataCadastro(new Date());
//		representante.getPessoaFisica().getPessoa().setCpfCnpj(cpf);
//	}
//
//	public void pesquisaRepresentante() {
//		cpfTemporario = representante.getPessoaFisica().getPessoa().getCpfCnpj();
//		
//		if (cpfTemporario.replaceAll("\\D", "").length() != 11) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//			return;
//		}
//		
//		representante = representanteDAO.pesquisaRepresentantePorCpfCnpjEPJ(representante.getPessoaFisica().getPessoa().getCpfCnpj(), 
//				locatarioPJ.getId());
//		
//		if (representante == null) {
//			this.novoPessoaRepresentante(cpfTemporario);
//			if (representante.getPessoaJuridica().getId() == null) {
//				representante.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
//				representante.getPessoaFisica().setPessoa(locatarioDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario));
//			} 
//		}
//		this.verificaEstadoCivil(1);
//	}
//
//	public void salvarRepresentante() {
//		try {
//			pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(representante.getPessoaFisica().getPessoa()));
//			representante.getPessoaFisica().setPessoa(pessoaDAO.getRetornaId());
//
//			representante.getPessoaFisica().setPessoa(pessoaDAO.getRetornaId());
//			pessoaFisicaDAO.merge(representante.getPessoaFisica());
//			representante.setPessoaFisica(pessoaFisicaDAO.getRetornaId());
//
//			if (locatario.getId() == null) {
//				listaRepresentante.add(representante);
//			} else {
//				representante.setPessoaJuridica(locatarioPJ.getPessoaJuridica());
//				representanteDAO.merge(representante);
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(locatarioPJ.getPessoaJuridica().getId());
//			}
//			
//			if (representante.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//				relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
//			}
//
//			PrimeFaces.current().executeScript("PF('dlgRepresentate').hide();");
//			PrimeFaces.current().ajax().update("FormCadLocatario:pnlRepresentante");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void editarRepresentante(ActionEvent evento) {
//		representante = (PessoaJuridicaRepresentante) evento.getComponent().getAttributes().get("representanteSelecionado");
//		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//		listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//		this.verificaEstadoCivil(1);
//	}
//
//	public void salvarLocatario() {
//		try {
//			locatario.setPessoa(ConverteCaixaAlta.convertePessoa(locatario.getPessoa()));
//			locatarioDAO.merge(locatario);
//
//			if (locatarioDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18
//					|| locatarioDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 14) {
//
//				if (locatarioDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18) {
//					locatarioPJ.setLocatario(locatarioDAO.getRetornaId());
//					locatarioPJ.getPessoaJuridica().setPessoa(locatarioDAO.getRetornaId().getPessoa());
//					locatarioPJDAO.merge(locatarioPJ);
//
//					for (PessoaJuridicaRepresentante temp : listaRepresentante) {
//						temp.setPessoaJuridica(locatarioPJDAO.getRetornaId().getPessoaJuridica());
//						temp.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(temp.getPessoaFisica()));
//						temp.getPessoaFisica().setPessoa(ConverteCaixaAlta.convertePessoa(temp.getPessoaFisica().getPessoa()));
//						representanteDAO.merge(temp);
//
//						pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(representanteDAO.getRetornaId().getPessoaFisica().getPessoa()));
//					}
//				} else {
//					locatarioPF.setLocatario(locatarioDAO.getRetornaId());
//					locatarioPF.getPessoaFisica().setPessoa(locatarioDAO.getRetornaId().getPessoa());
//					locatarioPFDAO.merge(locatarioPF);
//					
//					locatarioPF = locatarioPFDAO.getRetornaId();
//					locatarioPF.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(locatarioPF.getPessoaFisica()));
//					locatarioPFDAO.merge(locatarioPF);
//					
//					if (relacionamento.getPessoa().getId() == null) {
//						relacionamento.setPessoa(locatarioPFDAO.getRetornaId().getPessoaFisica());
//					}
//					
//					if (locatarioPFDAO.getRetornaId().getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//						relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
//					}
//				}
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//				return;
//			}
//			this.listar();
//
//			PrimeFaces.current().executeScript("PF('dlgLocatario').hide();");
//			PrimeFaces.current().ajax().update("frmMVD");
//
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Locatario(a) Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		locatario = (Locatario) evento.getComponent().getAttributes().get("locatarioSelecionado");
//		
//		if (locatario.getPessoa().getCpfCnpj().length() == 14) {
//			locatarioPF = locatarioPFDAO.pesquisaLocatarioPFPorLocatario(locatario.getId());
//			listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//			listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//			listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//			this.verificaEstadoCivil(2);
//
//		} else if (locatario.getPessoa().getCpfCnpj().length() == 18) {
//			locatarioPJ = locatarioPJDAO.pesquisaLocatarioPJPorLocatario(locatario.getId());
//			listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//			listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(locatarioPJ.getPessoaJuridica().getId());
//		}
//	}
//
//	public void consultaCEP(int opcao) throws IOException {
//		try {
//			ConsultaCEP consultaCEP = new ConsultaCEP();
//			if (opcao == 1 || opcao == 2) {
//				consultaCEP.pesquisaCEP(locatario.getPessoa().getCep());
//				locatario.getPessoa().setLogradouro(consultaCEP.getLogradouro());
//				locatario.getPessoa().setBairro(consultaCEP.getBairro());
//				locatario.getPessoa().setCidade(consultaCEP.getLocalidade());
//				locatario.getPessoa().setEstadoSigla(consultaCEP.getUf());
//
//				if (opcao == 1) {
//					PrimeFaces.current().ajax().update("FormCadLocatario:pnlEnderecoPf");
//				} else if (opcao == 2) {
//					PrimeFaces.current().ajax().update("FormCadLocatario:pnlEnderecoPj");
//				}
//				return;
//
//			} else if (opcao == 3) {
//				consultaCEP.pesquisaCEP(representante.getPessoaFisica().getPessoa().getCep());
//				representante.getPessoaFisica().getPessoa().setLogradouro(consultaCEP.getLogradouro());
//				representante.getPessoaFisica().getPessoa().setBairro(consultaCEP.getBairro());
//				representante.getPessoaFisica().getPessoa().setCidade(consultaCEP.getLocalidade());
//				representante.getPessoaFisica().getPessoa().setEstadoSigla(consultaCEP.getUf());
//
//				PrimeFaces.current().ajax().update("FormCadRepresentate:pnlEnderecoRepresentante");
//				return;
//				
//			} else if (opcao == 4) {
//				consultaCEP.pesquisaCEP(conjuge.getPessoa().getCep());
//				conjuge.getPessoa().setLogradouro(consultaCEP.getLogradouro());
//				conjuge.getPessoa().setBairro(consultaCEP.getBairro());
//				conjuge.getPessoa().setCidade(consultaCEP.getLocalidade());
//				conjuge.getPessoa().setEstadoSigla(consultaCEP.getUf());
//
//				PrimeFaces.current().ajax().update("FormCadConjuge:pnlEnderecoConjuge");
//				return;
//			}
//		} catch (RuntimeException erro) {
////			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
////			erro.printStackTrace();
//		}
//	}
//	
//	public void excluirRepresentante(ActionEvent evento) {
//		representante = (PessoaJuridicaRepresentante) evento.getComponent().getAttributes().get("representanteSelecionado");
//		try {
//			if (locatario.getId() == null) {
//				int achou = -1;
//				for (int posicao = 0; posicao < listaRepresentante.size(); posicao++) {
//					if (listaRepresentante.get(posicao).getPessoaFisica().getPessoa().equals(representante.getPessoaFisica().getPessoa())) {
//						achou = posicao;
//					}
//				}
//				if (achou > -1) {
//					listaRepresentante.remove(achou);
//				}
//			} else {
//				representanteDAO.excluir(representante);
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(locatarioPJ.getPessoaJuridica().getId());
//			}
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Representante Removido com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public Locatario getLocatario() {
//		return locatario;
//	}
//	public void setLocatario(Locatario locatario) {
//		this.locatario = locatario;
//	}
//	public List<Locatario> getListaLocatario() {
//		return listaLocatario;
//	}
//	public void setListaLocatario(List<Locatario> listaLocatario) {
//		this.listaLocatario = listaLocatario;
//	}
//	public List<Locatario> getListaLocatarioFiltrados() {
//		return listaLocatarioFiltrados;
//	}
//	public void setListaLocatarioFiltrados(List<Locatario> listaLocatarioFiltrados) {
//		this.listaLocatarioFiltrados = listaLocatarioFiltrados;
//	}
//	public List<EnumTipoDocumento> getListaEnumTipoDocumento() {
//		return listaEnumTipoDocumento;
//	}
//	public void setListaEnumTipoDocumento(List<EnumTipoDocumento> listaEnumTipoDocumento) {
//		this.listaEnumTipoDocumento = listaEnumTipoDocumento;
//	}
//	public List<EnumEstadoCivil> getListaEnumEstadoCivil() {
//		return listaEnumEstadoCivil;
//	}
//	public void setListaEnumEstadoCivil(List<EnumEstadoCivil> listaEnumEstadoCivil) {
//		this.listaEnumEstadoCivil = listaEnumEstadoCivil;
//	}
//	public LocatarioPessoaFisica getLocatarioPF() {
//		return locatarioPF;
//	}
//	public void setLocatarioPF(LocatarioPessoaFisica locatarioPF) {
//		this.locatarioPF = locatarioPF;
//	}
//	public PessoaRelacionamento getRelacionamento() {
//		return relacionamento;
//	}
//	public void setRelacionamento(PessoaRelacionamento relacionamento) {
//		this.relacionamento = relacionamento;
//	}
//	public LocatarioPessoaJuridica getLocatarioPJ() {
//		return locatarioPJ;
//	}
//	public void setLocatarioPJ(LocatarioPessoaJuridica locatarioPJ) {
//		this.locatarioPJ = locatarioPJ;
//	}
//	public PessoaFisica getConjuge() {
//		return conjuge;
//	}
//	public void setConjuge(PessoaFisica conjuge) {
//		this.conjuge = conjuge;
//	}
//	public List<PessoaFisica> getListaConjuge() {
//		return listaConjuge;
//	}
//	public void setListaConjuge(List<PessoaFisica> listaConjuge) {
//		this.listaConjuge = listaConjuge;
//	}
//	public Cnae getCnae() {
//		return cnae;
//	}
//	public void setCnae(Cnae cnae) {
//		this.cnae = cnae;
//	}
//	public List<Cnae> getListaCnae() {
//		return listaCnae;
//	}
//	public void setListaCnae(List<Cnae> listaCnae) {
//		this.listaCnae = listaCnae;
//	}
//	public Profissao getProfissao() {
//		return profissao;
//	}
//	public void setProfissao(Profissao profissao) {
//		this.profissao = profissao;
//	}
//	public List<Profissao> getListaProfissao() {
//		return listaProfissao;
//	}
//	public void setListaProfissao(List<Profissao> listaProfissao) {
//		this.listaProfissao = listaProfissao;
//	}
//	public PessoaJuridicaRepresentante getRepresentante() {
//		return representante;
//	}
//	public void setRepresentante(PessoaJuridicaRepresentante representante) {
//		this.representante = representante;
//	}
//	public List<PessoaJuridicaRepresentante> getListaRepresentante() {
//		return listaRepresentante;
//	}
//	public void setListaRepresentante(List<PessoaJuridicaRepresentante> listaRepresentante) {
//		this.listaRepresentante = listaRepresentante;
//	}
//}
