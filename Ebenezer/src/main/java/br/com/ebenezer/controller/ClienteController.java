package br.com.ebenezer.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import br.com.ebenezer.dao.ClienteDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.Cliente;
import br.com.ebenezer.model.enums.EnumEstadoCivil;
import br.com.ebenezer.model.enums.EnumTipoDocumento;
import br.com.ebenezer.webservice.ConsultaCEP;

@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {
	private static final long serialVersionUID = 1723370882969737431L;
	
	private Cliente cliente;
	private List<Cliente> listaCliente;
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	private List<EnumTipoDocumento> listaEnumTipoDocumento;
	private List<EnumEstadoCivil> listaEnumEstadoCivil;
	
	
	@PostConstruct
	public void listar() {
		try {
			listaCliente = clienteDAO.listar();
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro no metodo listar de ClienteController " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		
		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
	}
	
	public void salvar() {
		try {
			clienteDAO.merge(cliente);
			cliente = clienteDAO.getRetornaId();
			
			this.listar();
			
			PrimeFaces.current().executeScript("PF('dlgCliente').hide();");
			PrimeFaces.current().ajax().update("frmMVD");
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Cliente(a) Gravado com Sucesso!");
		}catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro no metodo salvar de clientecontroler " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		cliente = (Cliente)evento.getComponent().getAttributes().get("clienteSelecionado");
		listaEnumTipoDocumento = Arrays.asList(EnumTipoDocumento.values());
		listaEnumEstadoCivil = Arrays.asList(EnumEstadoCivil.values());
	}

	public void consultaCEP() throws IOException {
	ConsultaCEP consultaCEP = new ConsultaCEP();
	consultaCEP.pesquisaCEP(cliente.getCep());
	cliente.setBairro(consultaCEP.getBairro());
	cliente.setCidade(consultaCEP.getLocalidade());
	cliente.setEstadoSigla(consultaCEP.getUf());
	cliente.setLogradouro(consultaCEP.getLogradouro());
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	public List<EnumTipoDocumento> getListaEnumTipoDocumento() {
		return listaEnumTipoDocumento;
	}
	public void setListaEnumTipoDocumento(List<EnumTipoDocumento> listaEnumTipoDocumento) {
		this.listaEnumTipoDocumento = listaEnumTipoDocumento;
	}
	public List<EnumEstadoCivil> getListaEnumEstadoCivil() {
		return listaEnumEstadoCivil;
	}
	public void setListaEnumEstadoCivil(List<EnumEstadoCivil> listaEnumEstadoCivil) {
		this.listaEnumEstadoCivil = listaEnumEstadoCivil;
	}
}
