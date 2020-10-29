package br.com.ebenezer.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import br.com.ebenezer.dao.CnaeDAO;
import br.com.ebenezer.dao.CredorDAO;
import br.com.ebenezer.dao.CredorPessoaFisicaDAO;
import br.com.ebenezer.dao.CredorPessoaJuridicaDAO;
import br.com.ebenezer.dao.PessoaDAO;
import br.com.ebenezer.dao.PessoaFisicaDAO;
import br.com.ebenezer.dao.PessoaJuridicaDAO;
import br.com.ebenezer.dao.PessoaJuridicaRepresentanteDAO;
import br.com.ebenezer.dao.PessoaRelacionamentoDAO;
import br.com.ebenezer.dao.ProfissaoDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.Cnae;
import br.com.ebenezer.model.Credor;
import br.com.ebenezer.model.CredorPessoaFisica;
import br.com.ebenezer.model.CredorPessoaJuridica;
import br.com.ebenezer.model.Pessoa;
import br.com.ebenezer.model.Profissao;
import br.com.ebenezer.model.enums.EnumEstadoCivil;
import br.com.ebenezer.model.enums.EnumTipoDocumento;
import br.com.ebenezer.model.PessoaFisica;
import br.com.ebenezer.model.PessoaJuridicaRepresentante;
import br.com.ebenezer.model.PessoaRelacionamento;
import br.com.ebenezer.util.ConverteCaixaAlta;
import br.com.ebenezer.webservice.ConsultaCEP;

@ManagedBean
@ViewScoped
public class CredorController implements Serializable {
	private static final long serialVersionUID = -8304268184224896552L;

	private Credor credor;
	private List<Credor> listaCredor;
	private List<Credor> listaCredorFiltrados;
	private CredorDAO credorDAO = new CredorDAO();

	private List<EnumTipoDocumento> listaEnumTipoDocumento;
	private List<EnumEstadoCivil> listaEnumEstadoCivil;

	private CredorPessoaFisica credorPF;
	private CredorPessoaFisicaDAO credorPFDAO = new CredorPessoaFisicaDAO();
	
	private PessoaRelacionamento relacionamento;
	private PessoaRelacionamentoDAO relacionamentoDAO = new PessoaRelacionamentoDAO(); 

	private CredorPessoaJuridica credorPJ;
	private CredorPessoaJuridicaDAO credorPJDAO = new CredorPessoaJuridicaDAO();

	private Pessoa pessoa;
	private PessoaDAO pessoaDAO = new PessoaDAO();

	private PessoaFisica conjuge;
	private List<PessoaFisica> listaConjuge;
	private PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
	
	private PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

	private Cnae cnae;
	private List<Cnae> listaCnae;
	private CnaeDAO cnaeDAO = new CnaeDAO();

	private Profissao profissao;
	private List<Profissao> listaProfissao;
	private ProfissaoDAO profissaoDAO = new ProfissaoDAO();

	private PessoaJuridicaRepresentante representante;
	private List<PessoaJuridicaRepresentante> listaRepresentante;
	private PessoaJuridicaRepresentanteDAO representanteDAO = new PessoaJuridicaRepresentanteDAO();
	
	private String cpfTemporario;
	private RelacionamentoPessoaController relacionamentoController = new RelacionamentoPessoaController();

	@PostConstruct
	public void listar() {
		try {
			listaCredor = credorDAO.listar();
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void novo() {
		credor = new Credor();
		credor.setDataCadastro(new Date());
	}
	
	public void retornaDadosRelacionamento(CredorPessoaFisica CredorPessoaFisica) {
		if (CredorPessoaFisica.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
			listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(credor.getPessoa().getCpfCnpj());
			
			relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(credorPF.getPessoaFisica().getId());
			if (relacionamento == null) {
				this.novoRelacionamento();
				relacionamento.setPessoa(credorPF.getPessoaFisica());
			}
		}
	}

	public void pesquisaCpfCnpj() {
		try {
			cpfTemporario = credor.getPessoa().getCpfCnpj();
			
			if (credor.getPessoa().getCpfCnpj().length() == 14) {
				listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
				listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();

			} else if (credor.getPessoa().getCpfCnpj().length() == 18) {
				listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
				listaRepresentante = new ArrayList<>();
			} else {
				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
				return;
			}

			credor = credorDAO.pesquisaCredorPorCpfCnpj(cpfTemporario);
			pessoa = credorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);

			if (credor == null) {
				this.novo();
				credor.getPessoa().setCpfCnpj(cpfTemporario);
			} else {
				credor.setPessoa(ConverteCaixaAlta.convertePessoa(credor.getPessoa()));
				if (credor.getPessoa().getCpfCnpj().length() == 14) {
				}
			}
			
			if (pessoa != null) {
				credor.setPessoa(pessoa);
			}
			
			if (credor.getId() == null && credor.getPessoa().getCpfCnpj().length() == 14) {
				credorPF = new CredorPessoaFisica();
				credorPF.setDataCadastro(new Date());
				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
				if (pessoa != null) {
					credorPF.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
					this.retornaDadosRelacionamento(credorPF);
				}
				return;
			} else if (credor.getId() != null && credor.getPessoa().getCpfCnpj().length() == 14) {
				credorPF = credorDAO.pesquisaCredorPFPorIdCredor(credor.getId());
				this.retornaDadosRelacionamento(credorPF);
				listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
				return;
				
			} else if (credor.getId() == null && credor.getPessoa().getCpfCnpj().length() == 18) {
				credorPF = null;
				credorPJ = new CredorPessoaJuridica();
				credorPJ.setDataCadastro(new Date());
				if (pessoa != null) {
					credorPJ.setPessoaJuridica(pessoaJuridicaDAO.pesquisaPessoaJuridicaPorCpfCnpj(cpfTemporario));
					listaRepresentante = representanteDAO.listaRepresentantePorIDPessoa(pessoa.getId());
				}
				return;
			} else if (credor.getId() != null && credor.getPessoa().getCpfCnpj().length() == 18) {
				credorPJ = credorDAO.pesquisaCredorPJPorIdCredor(credor.getId());
				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(credorPJ.getPessoaJuridica().getId());
				return;
			}
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void verificaEstadoCivil(int opcao) {
		if (credor.getPessoa().getCpfCnpj().length() == 18) {
			if (representante.getPessoaFisica().getEstadoCivil() == null) {
				return;
			}
		}
		
		if (opcao == 1) {
			if (representante.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(representante.getPessoaFisica().getPessoa().getCpfCnpj());
				
				relacionamento = relacionamentoDAO.pesquisaRelacionamentoEsquerdaPorIdPessoa(representante.getPessoaFisica().getId());
				if (relacionamento == null) {
					this.novoRelacionamento();
				}
				relacionamento.setPessoa(representante.getPessoaFisica());
//				PrimeFaces.current().ajax().update("FormCadRepresentate:pnlConjuge");
			}
			
		} else if (opcao == 2) {
			this.retornaDadosRelacionamento(credorPF);
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
		pessoa = credorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario);
		
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
	
	public void salvarConjuge() {
		try {
			pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(conjuge.getPessoa()));
			conjuge.setPessoa(pessoaDAO.getRetornaId());
			
			pessoaFisicaDAO.merge(ConverteCaixaAlta.convertePessoaFisica(conjuge));
			
			relacionamento.setConjuge(pessoaFisicaDAO.getRetornaId());
			this.verificaRelacionamento();
			PrimeFaces.current().executeScript("PF('dlgConjuge').hide();");
			
			if (credor.getPessoa().getCpfCnpj().length() == 18) {
				PrimeFaces.current().ajax().update("FormCadRepresentate");
				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(
						representante.getPessoaFisica().getPessoa().getCpfCnpj());
				
			} else if (credor.getPessoa().getCpfCnpj().length() == 14) {
				PrimeFaces.current().ajax().update("FormCadCredor:pnlConjugeMain");
				listaConjuge = pessoaFisicaDAO.listarPessoaFisicaOrdenadoExcluindoEu(
						credorPF.getPessoaFisica().getPessoa().getCpfCnpj());
			}
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void novaProfissao() {
		profissao = new Profissao();
		profissao.setDataCadastro(new Date());
	}

	public void salvarProfissao() {
		try {
			profissao.setDescricao(profissao.getDescricao().toUpperCase());
			profissaoDAO.merge(profissao);
			listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
			
			if (credorPF != null) {
				credorPF.getPessoaFisica().setProfissao(profissaoDAO.getRetornaId());
			}

			if (representante != null) {
				representante.getPessoaFisica().setProfissao(profissaoDAO.getRetornaId());
			}

			PrimeFaces.current().executeScript("PF('dlgProfissao').hide();");
		} catch (RuntimeException erro) {
			if (erro.getMessage().equals("could not execute statement")) {
				MsgFeedBackUsuario.AdicionaMensagemErro("Profissão já Cadastrada!");
			} else {
				MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
				erro.printStackTrace();
			}
		}
	}

	public void novoCnae() {
		cnae = new Cnae();
		cnae.setDataCadastro(new Date());
	}

	public void salvarCnae() {
		try {
			cnae.setDescricao(cnae.getDescricao().toUpperCase());
			cnaeDAO.merge(cnae);
			listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
			credorPJ.getPessoaJuridica().setCnae(cnaeDAO.getRetornaId());

			PrimeFaces.current().executeScript("PF('dlgCnae').hide();");
		} catch (RuntimeException erro) {
			if (erro.getMessage().equals("could not execute statement")) {
				MsgFeedBackUsuario.AdicionaMensagemErro("Cnae já Cadastrado!");
			} else {
				MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
				erro.printStackTrace();
			}
		}
	}
	
	public void verificaRelacionamento() {
		if (credor.getPessoa().getCpfCnpj().length() == 18) {
			relacionamento.setPessoa(representante.getPessoaFisica());
		} else if (credor.getPessoa().getCpfCnpj().length() == 14) {
			relacionamento.setPessoa(credorPF.getPessoaFisica());
		}
	}

	public void novoRepresentante() {
		representante = new PessoaJuridicaRepresentante();
		representante.setDataCadastro(new Date());
		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
		listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
	}

	public void novoPessoaRepresentante(String cpf) {
		representante = new PessoaJuridicaRepresentante();
		representante.setDataCadastro(new Date());
		representante.getPessoaFisica().setPessoa(new Pessoa());
		representante.getPessoaFisica().getPessoa().setDataCadastro(new Date());
		representante.getPessoaFisica().getPessoa().setCpfCnpj(cpf);
	}

	public void pesquisaRepresentante() {
		cpfTemporario = representante.getPessoaFisica().getPessoa().getCpfCnpj();
		
		if (cpfTemporario.replaceAll("\\D", "").length() != 11) {
			MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
			return;
		}
		
		representante = representanteDAO.pesquisaRepresentantePorCpfCnpjEPJ(representante.getPessoaFisica().getPessoa().getCpfCnpj(), 
				credorPJ.getId());
		
		if (representante == null) {
			this.novoPessoaRepresentante(cpfTemporario);
			if (representante.getPessoaJuridica().getId() == null) {
				representante.setPessoaFisica(pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(cpfTemporario));
				representante.getPessoaFisica().setPessoa(credorDAO.pesquisaPessoaPorCpfCnpj(cpfTemporario));
			} 
		}
		this.verificaEstadoCivil(1);
	}

	public void salvarRepresentante() {
		try {
			pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(representante.getPessoaFisica().getPessoa()));
			representante.getPessoaFisica().setPessoa(pessoaDAO.getRetornaId());

			representante.getPessoaFisica().setPessoa(pessoaDAO.getRetornaId());
			pessoaFisicaDAO.merge(representante.getPessoaFisica());
			representante.setPessoaFisica(pessoaFisicaDAO.getRetornaId());

			if (credor.getId() == null) {
				listaRepresentante.add(representante);
			} else {
				representante.setPessoaJuridica(credorPJ.getPessoaJuridica());
				representanteDAO.merge(representante);
				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(credorPJ.getPessoaJuridica().getId());
			}
			
			if (representante.getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
				relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
			}

			PrimeFaces.current().executeScript("PF('dlgRepresentate').hide();");
			PrimeFaces.current().ajax().update("FormCadCredor:pnlRepresentante");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void editarRepresentante(ActionEvent evento) {
		representante = (PessoaJuridicaRepresentante) evento.getComponent().getAttributes().get("representanteSelecionado");
		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
		listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
		this.verificaEstadoCivil(1);
	}

	public void salvarCredor() {
		try {
			credor.setPessoa(ConverteCaixaAlta.convertePessoa(credor.getPessoa()));
			credorDAO.merge(credor);

			if (credorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18
					|| credorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 14) {

				if (credorDAO.getRetornaId().getPessoa().getCpfCnpj().length() == 18) {
					credorPJ.setCredor(credorDAO.getRetornaId());
					credorPJ.getPessoaJuridica().setPessoa(credorDAO.getRetornaId().getPessoa());
					credorPJDAO.merge(credorPJ);

					for (PessoaJuridicaRepresentante temp : listaRepresentante) {
						temp.setPessoaJuridica(credorPJDAO.getRetornaId().getPessoaJuridica());
						temp.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(temp.getPessoaFisica()));
						temp.getPessoaFisica().setPessoa(ConverteCaixaAlta.convertePessoa(temp.getPessoaFisica().getPessoa()));
						representanteDAO.merge(temp);

						pessoaDAO.merge(ConverteCaixaAlta.convertePessoa(representanteDAO.getRetornaId().getPessoaFisica().getPessoa()));
					}
				} else {
					credorPF.setCredor(credorDAO.getRetornaId());
					credorPF.getPessoaFisica().setPessoa(credorDAO.getRetornaId().getPessoa());
					credorPFDAO.merge(credorPF);
					
					credorPF = credorPFDAO.getRetornaId();
					credorPF.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(credorPF.getPessoaFisica()));
					credorPFDAO.merge(credorPF);
					
					if (relacionamento.getPessoa().getId() == null) {
						relacionamento.setPessoa(credorPFDAO.getRetornaId().getPessoaFisica());
					}
					
					if (credorPFDAO.getRetornaId().getPessoaFisica().getEstadoCivil().equals("CASADO(A)")) {
						relacionamentoController.verificaRelacionamento(relacionamento.getPessoa(), relacionamento.getConjuge());
					}
				}
			} else {
				MsgFeedBackUsuario.AdicionaMensagemErro("CPF/CNPJ Inválido!");
				return;
			}
			this.listar();

			PrimeFaces.current().executeScript("PF('dlgCredor').hide();");
			PrimeFaces.current().ajax().update("frmMVD");

			MsgFeedBackUsuario.AdicionaMensagemSucesso("Credor(a) Gravado com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		credor = (Credor) evento.getComponent().getAttributes().get("credorSelecionado");
		
		if (credor.getPessoa().getCpfCnpj().length() == 14) {
			credorPF = credorPFDAO.pesquisaCredorPFPorCredor(credor.getId());
			listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
			listaProfissao = profissaoDAO.listaProfissaoOrdenadoPorNone();
			listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
			this.verificaEstadoCivil(2);

		} else if (credor.getPessoa().getCpfCnpj().length() == 18) {
			credorPJ = credorPJDAO.pesquisaCredorPJPorCredor(credor.getId());
			listaCnae = cnaeDAO.listaCnaeOrdenadoPorNone();
			listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(credorPJ.getPessoaJuridica().getId());
		}
	}

	public void consultaCEP(int opcao) throws IOException {
		try {
			ConsultaCEP consultaCEP = new ConsultaCEP();
			if (opcao == 1 || opcao == 2) {
				consultaCEP.pesquisaCEP(credor.getPessoa().getCep());
				credor.getPessoa().setLogradouro(consultaCEP.getLogradouro());
				credor.getPessoa().setBairro(consultaCEP.getBairro());
				credor.getPessoa().setCidade(consultaCEP.getLocalidade());
				credor.getPessoa().setEstadoSigla(consultaCEP.getUf());

				if (opcao == 1) {
					PrimeFaces.current().ajax().update("FormCadCredor:pnlEnderecoPf");
				} else if (opcao == 2) {
					PrimeFaces.current().ajax().update("FormCadCredor:pnlEnderecoPj");
				}
				return;

			} else if (opcao == 3) {
				consultaCEP.pesquisaCEP(representante.getPessoaFisica().getPessoa().getCep());
				representante.getPessoaFisica().getPessoa().setLogradouro(consultaCEP.getLogradouro());
				representante.getPessoaFisica().getPessoa().setBairro(consultaCEP.getBairro());
				representante.getPessoaFisica().getPessoa().setCidade(consultaCEP.getLocalidade());
				representante.getPessoaFisica().getPessoa().setEstadoSigla(consultaCEP.getUf());

				PrimeFaces.current().ajax().update("FormCadRepresentate:pnlEnderecoRepresentante");
				return;
				
			} else if (opcao == 4) {
				consultaCEP.pesquisaCEP(conjuge.getPessoa().getCep());
				conjuge.getPessoa().setLogradouro(consultaCEP.getLogradouro());
				conjuge.getPessoa().setBairro(consultaCEP.getBairro());
				conjuge.getPessoa().setCidade(consultaCEP.getLocalidade());
				conjuge.getPessoa().setEstadoSigla(consultaCEP.getUf());

				PrimeFaces.current().ajax().update("FormCadConjuge:pnlEnderecoConjuge");
				return;
			}
		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
		}
	}
	
	public void excluirRepresentante(ActionEvent evento) {
		representante = (PessoaJuridicaRepresentante) evento.getComponent().getAttributes().get("representanteSelecionado");
		try {
			if (credor.getId() == null) {
				int achou = -1;
				for (int posicao = 0; posicao < listaRepresentante.size(); posicao++) {
					if (listaRepresentante.get(posicao).getPessoaFisica().getPessoa().equals(representante.getPessoaFisica().getPessoa())) {
						achou = posicao;
					}
				}
				if (achou > -1) {
					listaRepresentante.remove(achou);
				}
			} else {
				representanteDAO.excluir(representante);
				listaRepresentante = representanteDAO.listaRepresentantePorIDPJ(credorPJ.getPessoaJuridica().getId());
			}
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Representante Removido com Sucesso!");
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public Credor getCredor() {
		return credor;
	}
	public void setCredor(Credor Credor) {
		this.credor = Credor;
	}
	public List<Credor> getListaCredor() {
		return listaCredor;
	}
	public void setListaCredor(List<Credor> listaCredor) {
		this.listaCredor = listaCredor;
	}
	public List<Credor> getListaCredorFiltrados() {
		return listaCredorFiltrados;
	}
	public void setListaCredorFiltrados(List<Credor> listaCredorFiltrados) {
		this.listaCredorFiltrados = listaCredorFiltrados;
	}
	public CredorPessoaFisica getcredorPF() {
		return credorPF;
	}
	public void setcredorPF(CredorPessoaFisica credorPF) {
		this.credorPF = credorPF;
	}
	public CredorPessoaJuridica getcredorPJ() {
		return credorPJ;
	}
	public void setcredorPJ(CredorPessoaJuridica credorPJ) {
		this.credorPJ = credorPJ;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<EnumTipoDocumento> getListaEnumTipoDocumento() {
		return listaEnumTipoDocumento;
	}
	public void setListaEnumTipoDocumento(List<EnumTipoDocumento> listaEnumTipoDocumento) {
		this.listaEnumTipoDocumento = listaEnumTipoDocumento;
	}
	public Cnae getCnae() {
		return cnae;
	}
	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}
	public List<Cnae> getListaCnae() {
		return listaCnae;
	}
	public void setListaCnae(List<Cnae> listaCnae) {
		this.listaCnae = listaCnae;
	}
	public Profissao getProfissao() {
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public List<Profissao> getListaProfissao() {
		return listaProfissao;
	}
	public void setListaProfissao(List<Profissao> listaProfissao) {
		this.listaProfissao = listaProfissao;
	}
	public PessoaJuridicaRepresentante getRepresentante() {
		return representante;
	}
	public void setRepresentante(PessoaJuridicaRepresentante representante) {
		this.representante = representante;
	}
	public List<PessoaJuridicaRepresentante> getListaRepresentante() {
		return listaRepresentante;
	}
	public void setListaRepresentante(List<PessoaJuridicaRepresentante> listaRepresentante) {
		this.listaRepresentante = listaRepresentante;
	}
	public PessoaFisica getConjuge() {
		return conjuge;
	}
	public void setConjuge(PessoaFisica conjuge) {
		this.conjuge = conjuge;
	}
	public List<EnumEstadoCivil> getListaEnumEstadoCivil() {
		return listaEnumEstadoCivil;
	}
	public void setListaEnumEstadoCivil(List<EnumEstadoCivil> listaEnumEstadoCivil) {
		this.listaEnumEstadoCivil = listaEnumEstadoCivil;
	}
	public List<PessoaFisica> getListaConjuge() {
		return listaConjuge;
	}
	public void setListaConjuge(List<PessoaFisica> listaConjuge) {
		this.listaConjuge = listaConjuge;
	}
	public PessoaRelacionamento getRelacionamento() {
		return relacionamento;
	}
	public void setRelacionamento(PessoaRelacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}
}