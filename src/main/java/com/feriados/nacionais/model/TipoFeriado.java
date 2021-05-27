package com.feriados.nacionais.model;

public enum TipoFeriado {
	
	CONFRATERNIZACAO_UNIVERSAL("Confraternização Universal"),
	TIRADENTES("Tiradentes"),
	DIA_DO_TRABALHO("Dia do Trabalho"),
	INDEPENDENCIA_D_BRASIL("Independência do Brasil"),
	NOSSA_SENHORA_APARECIDA("Nossa Senhora Aparecida"),
	FINADOS("Finados"),
	PROCLAMACAO_DA_REPUBLICA("Proclamação da República"),
	NATAL("Natal"),
	CARNAVAL("Carnaval"),
	SEXTA_FEIRA_SANTA("Sexta-Feira Santa"),
	PASCOA("Páscoa"),
	CORPUS_CHRISTI("Corpus Christi");
	
	private String nome;

	private TipoFeriado(String nome) {
		this.nome = nome;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
