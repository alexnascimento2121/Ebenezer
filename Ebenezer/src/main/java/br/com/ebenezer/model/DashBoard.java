package br.com.ebenezer.model;

import java.math.BigDecimal;

public class DashBoard extends GernericModel {
	private static final long serialVersionUID = 7330769873418403298L;

	private int quantidade;
	private BigDecimal valor;
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
