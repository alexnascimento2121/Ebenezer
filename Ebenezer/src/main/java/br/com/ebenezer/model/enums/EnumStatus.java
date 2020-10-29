package br.com.ebenezer.model.enums;

public enum EnumStatus {
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;
	
	EnumStatus(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
