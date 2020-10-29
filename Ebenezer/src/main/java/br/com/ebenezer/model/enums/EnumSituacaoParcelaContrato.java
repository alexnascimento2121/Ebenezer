package br.com.ebenezer.model.enums;

public enum EnumSituacaoParcelaContrato {
	
	ABERTO("ABERTO"),
	CANCELADO("CANCELADO"),
	PAGO("PAGO");

	private String descricao;
	
	private EnumSituacaoParcelaContrato(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
