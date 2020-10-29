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
//import br.com.ebenezer.dao.FiadorDAO;
//import br.com.ebenezer.dao.FiadorPessoaFisicaDAO;
//import br.com.ebenezer.dao.FiadorPessoaJuridicaDAO;
//import br.com.ebenezer.dao.PessoaDAO;
//import br.com.ebenezer.dao.PessoaFisicaDAO;
//import br.com.ebenezer.dao.PessoaJuridicaDAO;
//import br.com.ebenezer.dao.PessoaJuridicaRepresentanteDAO;
//import br.com.ebenezer.dao.PessoaRelacionamentoDAO;
//import br.com.ebenezer.dao.ProfissaoDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Cnae;
//import br.com.ebenezer.model.Fiador;
//import br.com.ebenezer.model.FiadorPessoaFisica;
//import br.com.ebenezer.model.FiadorPessoaJuridica;
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
//public class FiadorController implements Serializable {
//	private static final long serialVersionUID = -8304268184224896552L;
//
//	private Fiador fiador;
//	private List<Fiador> listaFiador;
//	private List<Fiador> listaFiadorFiltrados;
//	private FiadorDAO fiadorDAO = new FiadorDAO();
//
//	private List<EnumTipoDocumento> listaEnumTipoDocumento;
//	private List<EnumEstadoCivil> listaEnumEstadoCivil;
//
//	private FiadorPessoaFisica fiadorPF;
//	private FiadorPessoaFisicaDAO fiadorPFDAO = new FiadorPessoaFisicaDAO();
//	
//	private PessoaRelacionamento relacionamento;
//	private PessoaRelacionamentoDAO relacionamentoDAO = new PessoaRelacionamentoDAO(); 
//
//	private FiadorPessoaJuridica fiadorPJ;
//	private FiadorPessoaJuridicaDAO fiadorPJDAO = new FiadorPessoaJuridicaDAO();
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
//			listaFiador = fiadorDAO.listar();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		fiador = new Fiador();
//		fiador.setDataCadastro(new Date());
//	}
//	
//	public void retornaDadosRelacionamento(FiadorPessoaFisica fiadorPessoaFisica) {
//		if (fiadorPessoaFisica.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//			listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(fiador.getPessoa().getCpfCnpj());
//			
//			relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(fiadorPF.getPessoaFisica().getId());
//			if (relacionamento == null) {
//				this.novoRelacionamento();
//				relacionamento.setPessoa(fiadorPF.getPessoaFisica());
//			}
//		}
//	}
//
//	public void pesquisaCpfCnpj() {
//		try {
//			cpfTemporario = fiador.getPessoa().getCpfCnpj();
//			
//			if (fiador.getPessoa().getCpfCnpj().length() == 14) {
//				listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//				listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//
//			} else if (fiador.getPessoa().getCpfCnpj().length() == 18) {
//				listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//				listaRepresentante = new ArrayList<>();
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//				return;
//			}
//
//			fiador = fiadorDAO.pesquisaFiadorPorCpfCnpj(cpfTemporario);
//			pessoa = fiadorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
//
//			if (fiador == null) {
//				this.novo();
//				fiador.getPessoa().setCpfCnpj(cpfTemporario);
//			} else {
//				fiador.setPessoa(ConverteCaixaAlta.convertePessoa(fiador.getPessoa()));
//				if (fiador.getPessoa().getCpfCnpj().length() == 14) {
//				}
//			}
//			
//			if (pessoa != null) {
//				fiador.setPessoa(pessoa);
//			}
//			
//			if (fiador.getId() == null && fiador.getPessoa().getCpfCnpj().length() == 14) {
//				fiadorPF = new FiadorPessoaFisica();
//				fiadorPF.setDataCadastro(new Date());
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				if (pessoa != null) {
//					fiadorPF.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
//					this.retornaDadosRelacionamento(fiadorPF);
//				}
//				return;
//			} else if (fiador.getId() != null && fiador.getPessoa().getCpfCnpj().length() == 14) {
//				fiadorPF = fiadorDAO.pesquisaFiadorPFPorIdLocado(fiador.getId());
//				this.retornaDadosRelacionamento(fiadorPF);
//				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//				return;
//				
//			} else if (fiador.getId() == null && fiador.getPessoa().getCpfCnpj().length() == 18) {
//				fiadorPF = null;
//				fiadorPJ = new FiadorPessoaJuridica();
//				fiadorPJ.setDataCadastro(new Date());
//				if (pessoa != null) {
//					fiadorPJ.setPessoaJuridica(pessoaJuridicaDAO.pesquisaPessoaJuridicaPorCpfCnpj(cpfTemporario));
//					listaRepresentante = representanteDAO.listaRepresentantePorIDPessoa(pessoa.getId());
//				}
//				return;
//			} else if (fiador.getId() != null && fiador.getPessoa().getCpfCnpj().length() == 18) {
//				fiadorPJ = fiadorDAO.pesquisaFiadorPJPorIdLocado(fiador.getId());
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(fiadorPJ.getPessoaJuridica().getId());
//				return;
//			}
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//	
//	public void verificaEstadoCivil(int opcao) {
//		if (fiador.getPessoa().getCpfCnpj().length() == 18) {
//			if (representante.getPessoaFisica().getEstadoCivil() == null) {
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
////				PrimeFaces.current().ajax().update("FormCadRepresentate:pnlConjuge");
//			}
//			
//		} else if (opcao == 2) {
//			this.retornaDadosRelacionamento(fiadorPF);
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
//		pessoa = fiadorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
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
//			if (fiador.getPessoa().getCpfCnpj().length() == 18) {
//				PrimeFaces.current().ajax().update("FormCadRepresentate");
//				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(
//						representante.getPessoaFisica().getPessoa().getCpfCnpj());
//				
//			} else if (fiador.getPessoa().getCpfCnpj().length() == 14) {
//				PrimeFaces.current().ajax().update("FormCadFiador:pnlConjugeMain");
//				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(
//						fiadorPF.getPessoaFisica().getPessoa().getCpfCnpj());
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
//			if (fiadorPF != null) {
//				fiadorPF.getPessoaFisica().setProfissao(profissaoDAO.getRetornaId());
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
//			fiadorPJ.getPessoaJuridica().setCnae(cnaeDAO.getRetornaId());
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
//		if (fiador.getPessoa().getCpfCnpj().length() == 18) {
//			relacionamento.setPessoa(representante.getPessoaFisica());
//		} else if (fiador.getPessoa().getCpfCnpj().length() == 14) {
//			relacionamento.setPessoa(fiadorPF.getPessoaFisica());
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
//				fiadorPJ.getId());
//		
//		if (representante == null) {
//			this.novoPessoaRepresentante(cpfTemporario);
//			if (representante.getPessoaJuridica().getId() == null) {
//				representante.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
//				representante.getPessoaFisica().setPessoa(fiadorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario));
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
//			if (fiador.getId() == null) {
//				listaRepresentante.add(representante);
//			} else {
//				representante.setPessoaJuridica(fiadorPJ.getPessoaJuridica());
//				representanteDAO.merge(representante);
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(fiadorPJ.getPessoaJuridica().getId());
//			}
//			
//			if (representante.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//				relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
//			}
//
//			PrimeFaces.current().executeScript("PF('dlgRepresentate').hide();");
//			PrimeFaces.current().ajax().update("FormCadFiador:pnlRepresentante");
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
//	public void salvarFiador() {
//		try {
//			fiador.setPessoa(ConverteCaixaAlta.convertePessoa(fiador.getPessoa()));
//			fiadorDAO.merge(fiador);
//
//			if (fiadorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18
//					|| fiadorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 14) {
//
//				if (fiadorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18) {
//					fiadorPJ.setFiador(fiadorDAO.getRetornaId());
//					fiadorPJ.getPessoaJuridica().setPessoa(fiadorDAO.getRetornaId().getPessoa());
//					fiadorPJDAO.merge(fiadorPJ);
//
//					for (PessoaJuridicaRepresentante temp : listaRepresentante) {
//						temp.setPessoaJuridica(fiadorPJDAO.getRetornaId().getPessoaJuridica());
//						temp.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(temp.getPessoaFisica()));
//						temp.getPessoaFisica().setPessoa(ConverteCaixaAlta.convertePessoa(temp.getPessoaFisica().getPessoa()));
//						representanteDAO.merge(temp);
//
//						pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(representanteDAO.getRetornaId().getPessoaFisica().getPessoa()));
//					}
//				} else {
//					fiadorPF.setFiador(fiadorDAO.getRetornaId());
//					fiadorPF.getPessoaFisica().setPessoa(fiadorDAO.getRetornaId().getPessoa());
//					fiadorPFDAO.merge(fiadorPF);
//					
//					fiadorPF = fiadorPFDAO.getRetornaId();
//					fiadorPF.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(fiadorPF.getPessoaFisica()));
//					fiadorPFDAO.merge(fiadorPF);
//					
//					if (relacionamento.getPessoa().getId() == null) {
//						relacionamento.setPessoa(fiadorPFDAO.getRetornaId().getPessoaFisica());
//					}
//					
//					if (fiadorPFDAO.getRetornaId().getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
//						relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
//					}
//				}
//			} else {
//				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
//				return;
//			}
//			this.listar();
//
//			PrimeFaces.current().executeScript("PF('dlgFiador').hide();");
//			PrimeFaces.current().ajax().update("frmMVD");
//
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("fiador(a) Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		fiador = (Fiador) evento.getComponent().getAttributes().get("fiadorSelecionado");
//		
//		if (fiador.getPessoa().getCpfCnpj().length() == 14) {
//			fiadorPF = fiadorPFDAO.pesquisaFiadorPFPorFiador(fiador.getId());
//			listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//			listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
//			listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
//			this.verificaEstadoCivil(2);
//
//		} else if (fiador.getPessoa().getCpfCnpj().length() == 18) {
//			fiadorPJ = fiadorPJDAO.pesquisaFiadorPJPorFiador(fiador.getId());
//			listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
//			listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(fiadorPJ.getPessoaJuridica().getId());
//		}
//	}
//
//	public void consultaCEP(int opcao) throws IOException {
//		try {
//			ConsultaCEP consultaCEP = new ConsultaCEP();
//			if (opcao == 1 || opcao == 2) {
//				consultaCEP.pesquisaCEP(fiador.getPessoa().getCep());
//				fiador.getPessoa().setLogradouro(consultaCEP.getLogradouro());
//				fiador.getPessoa().setBairro(consultaCEP.getBairro());
//				fiador.getPessoa().setCidade(consultaCEP.getLocalidade());
//				fiador.getPessoa().setEstadoSigla(consultaCEP.getUf());
//
//				if (opcao == 1) {
//					PrimeFaces.current().ajax().update("FormCadFiador:pnlEnderecoPf");
//				} else if (opcao == 2) {
//					PrimeFaces.current().ajax().update("FormCadFiador:pnlEnderecoPj");
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
//			if (fiador.getId() == null) {
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
//				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(fiadorPJ.getPessoaJuridica().getId());
//			}
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Representante Removido com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public Fiador getFiador() {
//		return fiador;
//	}
//	public void setFiador(Fiador fiador) {
//		this.fiador = fiador;
//	}
//	public List<Fiador> getListaFiador() {
//		return listaFiador;
//	}
//	public void setListaFiador(List<Fiador> listaFiador) {
//		this.listaFiador = listaFiador;
//	}
//	public List<Fiador> getListaFiadorFiltrados() {
//		return listaFiadorFiltrados;
//	}
//	public void setListaFiadorFiltrados(List<Fiador> listaFiadorFiltrados) {
//		this.listaFiadorFiltrados = listaFiadorFiltrados;
//	}
//	public FiadorPessoaFisica getFiadorPF() {
//		return fiadorPF;
//	}
//	public void setFiadorPF(FiadorPessoaFisica fiadorPF) {
//		this.fiadorPF = fiadorPF;
//	}
//	public FiadorPessoaJuridica getFiadorPJ() {
//		return fiadorPJ;
//	}
//	public void setFiadorPJ(FiadorPessoaJuridica fiadorPJ) {
//		this.fiadorPJ = fiadorPJ;
//	}
//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}
//	public List<EnumTipoDocumento> getListaEnumTipoDocumento() {
//		return listaEnumTipoDocumento;
//	}
//	public void setListaEnumTipoDocumento(List<EnumTipoDocumento> listaEnumTipoDocumento) {
//		this.listaEnumTipoDocumento = listaEnumTipoDocumento;
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
//	public PessoaFisica getConjuge() {
//		return conjuge;
//	}
//	public void setConjuge(PessoaFisica conjuge) {
//		this.conjuge = conjuge;
//	}
//	public List<EnumEstadoCivil> getListaEnumEstadoCivil() {
//		return listaEnumEstadoCivil;
//	}
//	public void setListaEnumEstadoCivil(List<EnumEstadoCivil> listaEnumEstadoCivil) {
//		this.listaEnumEstadoCivil = listaEnumEstadoCivil;
//	}
//	public List<PessoaFisica> getListaConjuge() {
//		return listaConjuge;
//	}
//	public void setListaConjuge(List<PessoaFisica> listaConjuge) {
//		this.listaConjuge = listaConjuge;
//	}
//	public PessoaRelacionamento getRelacionamento() {
//		return relacionamento;
//	}
//	public void setRelacionamento(PessoaRelacionamento relacionamento) {
//		this.relacionamento = relacionamento;
//	}
//}