package br.com.ebenezer.model.enums;

public enum EnumTipoDocumento {
	
	RG("RG"),
	CTPS("CTPS"),
	CNH("CNH"),
	OAB("OAB");
	
	private String descricao;
	
	private EnumTipoDocumento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
