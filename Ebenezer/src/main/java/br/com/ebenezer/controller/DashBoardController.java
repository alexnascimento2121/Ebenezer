package br.com.ebenezer.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ebenezer.dao.DashBoardDAO;
import br.com.ebenezer.mensagem.MsgFeedBackUsuario;
import br.com.ebenezer.model.DashBoard;

@ManagedBean
@ViewScoped
public class DashBoardController implements Serializable {
	private static final long serialVersionUID = -3586069217887849799L;
	
	private DashBoard dashBoardReceberPorSemana;
	private DashBoard dashBoardReceberPorMes;
	private DashBoard dashBoardReceberPorAno;
	
	private DashBoard dashBoardRecebidoPorSemana;
	private DashBoard dashBoardRecebidoPorMes;
	private DashBoard dashBoardRecebidoPorAno;
	private DashBoardDAO dashBoardDAO = new DashBoardDAO();

	@PostConstruct
	public void listar() {
		try {
			dashBoardReceberPorSemana = dashBoardDAO.carregar("valorparcela", "datavencimento" ,"week", "ABERTO", new Date());
			dashBoardReceberPorMes = dashBoardDAO.carregar("valorparcela", "datavencimento", "month", "ABERTO", new Date());
			dashBoardReceberPorAno = dashBoardDAO.carregar("valorparcela", "datavencimento", "year", "ABERTO", new Date());
			
			dashBoardRecebidoPorSemana = dashBoardDAO.carregar("valorpago", "datapagamento" ,"week", "PAGO", new Date());
			dashBoardRecebidoPorMes = dashBoardDAO.carregar("valorpago", "datapagamento" ,"month", "PAGO", new Date());
			dashBoardRecebidoPorAno = dashBoardDAO.carregar("valorpago", "datapagamento" ,"year", "PAGO", new Date());
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Ocorreu um erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public DashBoard getDashBoardReceberPorSemana() {
		return dashBoardReceberPorSemana;
	}
	public void setDashBoardReceberPorSemana(DashBoard dashBoardReceberPorSemana) {
		this.dashBoardReceberPorSemana = dashBoardReceberPorSemana;
	}
	public DashBoard getDashBoardReceberPorMes() {
		return dashBoardReceberPorMes;
	}
	public void setDashBoardReceberPorMes(DashBoard dashBoardReceberPorMes) {
		this.dashBoardReceberPorMes = dashBoardReceberPorMes;
	}
	public DashBoard getDashBoardReceberPorAno() {
		return dashBoardReceberPorAno;
	}
	public void setDashBoardReceberPorAno(DashBoard dashBoardReceberPorAno) {
		this.dashBoardReceberPorAno = dashBoardReceberPorAno;
	}
	public DashBoard getDashBoardRecebidoPorSemana() {
		return dashBoardRecebidoPorSemana;
	}
	public void setDashBoardRecebidoPorSemana(DashBoard dashBoardRecebidoPorSemana) {
		this.dashBoardRecebidoPorSemana = dashBoardRecebidoPorSemana;
	}
	public DashBoard getDashBoardRecebidoPorMes() {
		return dashBoardRecebidoPorMes;
	}
	public void setDashBoardRecebidoPorMes(DashBoard dashBoardRecebidoPorMes) {
		this.dashBoardRecebidoPorMes = dashBoardRecebidoPorMes;
	}
	public DashBoard getDashBoardRecebidoPorAno() {
		return dashBoardRecebidoPorAno;
	}
	public void setDashBoardRecebidoPorAno(DashBoard dashBoardRecebidoPorAno) {
		this.dashBoardRecebidoPorAno = dashBoardRecebidoPorAno;
	}

}