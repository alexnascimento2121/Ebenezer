package br.com.ebenezer.model.enums;

public enum EnumSituacaoContrato {
	
	RASCUNHO("RASCUNHO"),
	EMVINGENCIA("EM VINGÊNCIA"),
	CANCELADO("CANCELADO"),
	CONCLUIDO("CONCLUÍDO");

	private String descricao;
	
	private EnumSituacaoContrato(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
