package com.mc.modelagem.domain.enums;

public enum TipoPagamento {

	BOLETO(1,"Boleto"),
	CARTAO(2,"Cartao");
	
	private int codigo;
	private String descricao;
	
	private TipoPagamento(int codigo,String descricao) {
		
		this.codigo = codigo;
		this.descricao = descricao;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPagamento toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(TipoPagamento x : TipoPagamento.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
