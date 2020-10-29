//package br.com.ebenezer.controller;
//
//import java.io.IOException;
//import java.io.Serializable;
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
//import br.com.ebenezer.dao.FuncaoDAO;
//import br.com.ebenezer.dao.FuncionarioDAO;
//import br.com.ebenezer.dao.PessoaFisicaDAO;
//import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
//import br.com.ebenezer.model.Funcao;
//import br.com.ebenezer.model.Funcionario;
//import br.com.ebenezer.model.enums.EnumTipoDocumento;
//import br.com.ebenezer.model.nova.PessoaFisica;
//import br.com.ebenezer.util.ConverteCaixaAlta;
//import br.com.ebenezer.webservice.ConsultaCEP;
//
//@ManagedBean
//@ViewScoped
//public class FuncionarioController implements Serializable {
//	private static final long serialVersionUID = 1248032107985828372L;
//	
//	private Funcionario funcionario;
//	private List<Funcionario> listaFuncionario;
//	private List<Funcionario> listaFuncionarioFiltrados;
//	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//	
//	private Funcao funcao;
//	private List<Funcao> listaFuncao;
//	private FuncaoDAO funcaoDAO = new FuncaoDAO();
//	
//	private List<EnumTipoDocumento> listaEnumTipoDocumento;
//	
//	private PessoaFisica pessoaFisica;
//	private PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
//	
//	@PostConstruct
//	public void listar() {
//		try {
//			listaFuncionario = funcionarioDAO.listar();
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void novo() {
//		funcionario = new Funcionario();
//		funcionario.setDataCadastro(new Date());
//		funcionario.getPessoaFisica().getPessoa().setDataCadastro(new Date());
//		
//		listaFuncao = funcaoDAO.listaFuncaoOrdenada();
//		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//	}
//	
//	public void pesquisar() {
//		String cpfTemporario = funcionario.getPessoaFisica().getPessoa().getCpfCnpj();
//		pessoaFisica = pessoaFisicaDAO.pesquisaPessoaFisicaPorCpfCnpj(funcionario.getPessoaFisica().getPessoa().getCpfCnpj());
//		if (pessoaFisica != null) {
//			funcionario.setPessoaFisica(pessoaFisica);
//		} else {
//			this.novo();
//			funcionario.getPessoaFisica().getPessoa().setCpfCnpj(cpfTemporario);
//		}
//	}
//
//	public void salvar() {
//		try {
//			funcionarioDAO.merge(funcionario);
//			funcionario = funcionarioDAO.getRetornaId();
//			
//			funcionario.getPessoaFisica().setPessoa(ConverteCaixaAlta.convertePessoa(funcionario.getPessoaFisica().getPessoa()));
//			funcionario.setPessoaFisica(ConverteCaixaAlta.convertePessoaFisica(funcionario.getPessoaFisica()));
//			funcionarioDAO.merge(funcionario);
//
//			this.listar();
//			
//			PrimeFaces.current().executeScript("PF('dlgFuncionario').hide();");
//			PrimeFaces.current().ajax().update("frmMVD");
//			
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Funcionário(a) Gravado com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public void editar(ActionEvent evento) {
//		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
//		
//		listaFuncao = funcaoDAO.listar();
//		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
//	}
//	
//	public void consultaCEP() throws IOException {
//		ConsultaCEP consultaCEP = new ConsultaCEP();
//		consultaCEP.pesquisaCEP(funcionario.getPessoaFisica().getPessoa().getCep());
//		funcionario.getPessoaFisica().getPessoa().setLogradouro(consultaCEP.getLogradouro());
//		funcionario.getPessoaFisica().getPessoa().setBairro(consultaCEP.getBairro());
//		funcionario.getPessoaFisica().getPessoa().setCidade(consultaCEP.getLocalidade());
//		funcionario.getPessoaFisica().getPessoa().setEstadoSigla(consultaCEP.getUf());
//	}
//	
//	public void novaFuncao() {
//		funcao = new Funcao();
//		funcao.setDataCadastro(new Date());
//	}
//
//	public void salvarFuncao() {
//		try {
//			funcao.setDescricao(funcao.getDescricao().toUpperCase());
//			funcaoDAO.merge(funcao);
//			listaFuncao = funcaoDAO.listaFuncaoOrdenada();
//			
//			funcionario.setFuncao(funcaoDAO.getRetornaId());
//			
//			MsgFeedBackUsuario.AdicionaMensagemSucesso("Função Gravada com Sucesso!");
//		} catch (RuntimeException erro) {
//			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
//			erro.printStackTrace();
//		}
//	}
//
//	public Funcionario getFuncionario() {
//		return funcionario;
//	}
//	public void setFuncionario(Funcionario funcionario) {
//		this.funcionario = funcionario;
//	}
//	public List<Funcionario> getListaFuncionario() {
//		return listaFuncionario;
//	}
//	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
//		this.listaFuncionario = listaFuncionario;
//	}
//	public List<Funcionario> getListaFuncionarioFiltrados() {
//		return listaFuncionarioFiltrados;
//	}
//	public void setListaFuncionarioFiltrados(List<Funcionario> listaFuncionarioFiltrados) {
//		this.listaFuncionarioFiltrados = listaFuncionarioFiltrados;
//	}
//	public List<Funcao> getListaFuncao() {
//		return listaFuncao;
//	}
//	public void setListaFuncao(List<Funcao> listaFuncao) {
//		this.listaFuncao = listaFuncao;
//	}
//	public List<EnumTipoDocumento> getListaEnumTipoDocumento() {
//		return listaEnumTipoDocumento;
//	}
//	public void setListaEnumTipoDocumento(List<EnumTipoDocumento> listaEnumTipoDocumento) {
//		this.listaEnumTipoDocumento = listaEnumTipoDocumento;
//	}
//	public Funcao getFuncao() {
//		return funcao;
//	}
//	public void setFuncao(Funcao funcao) {
//		this.funcao = funcao;
//	}
//}