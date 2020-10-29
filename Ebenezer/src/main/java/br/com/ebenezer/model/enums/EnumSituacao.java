package br.com.ebenezer.model.enums;

public enum EnumSituacao {
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;
	
	EnumSituacao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
