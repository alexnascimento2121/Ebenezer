package br.com.ebenezer.model.enums;

public enum EnumEstadoCivil {
	
	SOLTEIRO("SOLTEIRO(A)"),
	CASADO("CASADO(A)"),
	VIUVO("VIÚVO(A)"),
	DESQUITADO("DESQUITADO(A)"),
	DIVORCIADO("DIVORCIADO(A)"),
	SEPARADO("SEPARADO(A)"),
	OUTROS("OUTROS(A)");
	
	private String descricao;
	
	EnumEstadoCivil(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
