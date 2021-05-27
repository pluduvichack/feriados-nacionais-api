package com.feriados.nacionais.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FeriadoNacional {
	
	private int dia;
	
	private int mes;
	
	private int ano;
	
	private TipoFeriado feriado;
	
	private static final int[][] TABELA_VARIAVEIS = {{22,2},     {23,2},     {24,4},     {24,5},     {24,6},     {25,7}};
	
	private static final int[][] TABELA_ANOS      = {{1582,1699},{1700,1799},{1800,1899},{1900,2099},{2100,2199},{2200,2299}};	

	public FeriadoNacional(int dia, int mes, int ano, TipoFeriado feriado) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.feriado = feriado;
	}

	/**
	 * Retorna uma lista de todos os feriados nacionais, com datas fixas
	 * 
	 * @return Lista de todos os feriados naciocnais com datas fixas
	 */
    public static List<FeriadoNacional> getListaFeriadosNacionais() {
        List<FeriadoNacional> feriados = new ArrayList<FeriadoNacional>();
        feriados.add(new FeriadoNacional(1, Calendar.JANUARY, 0, TipoFeriado.CONFRATERNIZACAO_UNIVERSAL));
        feriados.add(new FeriadoNacional(21, Calendar.APRIL, 0, TipoFeriado.TIRADENTES));
        feriados.add(new FeriadoNacional(1, Calendar.MAY, 0, TipoFeriado.DIA_DO_TRABALHO));
        feriados.add(new FeriadoNacional(7, Calendar.SEPTEMBER, 0, TipoFeriado.INDEPENDENCIA_D_BRASIL));
        feriados.add(new FeriadoNacional(12, Calendar.OCTOBER, 0, TipoFeriado.NOSSA_SENHORA_APARECIDA));
        feriados.add(new FeriadoNacional(2, Calendar.NOVEMBER, 0, TipoFeriado.FINADOS));
        feriados.add(new FeriadoNacional(15, Calendar.NOVEMBER, 0, TipoFeriado.PROCLAMACAO_DA_REPUBLICA));
        feriados.add(new FeriadoNacional(25, Calendar.DECEMBER, 0, TipoFeriado.NATAL));

        return feriados;
    }
    
    /**
     * Retorna uma lista de todos os feriados nacionais, com datas variáveis de acordo com o ano recebido como parâmetro
     * @param ano serve como base para retornar os feriados
     * 
     * @return Lista de todos os feriados nacionais, com datas variáveis de acordo com o ano recebido como parâmetro
     */
	public static List<FeriadoNacional> getFeriadosBaseadosNoCarnaval(int ano) {
		List<FeriadoNacional> feriados = new ArrayList<FeriadoNacional>();
		Calendar dataFeriado = null;

		dataFeriado = getDataVariavel(ano, TipoFeriado.PASCOA);
		feriados.add(new FeriadoNacional(dataFeriado.get(Calendar.DAY_OF_MONTH), dataFeriado.get(Calendar.MONTH), dataFeriado.get(Calendar.YEAR), TipoFeriado.PASCOA));		
		
		
		dataFeriado = getDataVariavel(ano, TipoFeriado.CARNAVAL);
		feriados.add(new FeriadoNacional(dataFeriado.get(Calendar.DAY_OF_MONTH), dataFeriado.get(Calendar.MONTH), dataFeriado.get(Calendar.YEAR), TipoFeriado.CARNAVAL));		
		
		dataFeriado = getDataVariavel(ano, TipoFeriado.SEXTA_FEIRA_SANTA);
		feriados.add(new FeriadoNacional(dataFeriado.get(Calendar.DAY_OF_MONTH), dataFeriado.get(Calendar.MONTH), dataFeriado.get(Calendar.YEAR), TipoFeriado.SEXTA_FEIRA_SANTA));		
		
		dataFeriado = getDataVariavel(ano, TipoFeriado.CORPUS_CHRISTI);
		feriados.add(new FeriadoNacional(dataFeriado.get(Calendar.DAY_OF_MONTH), dataFeriado.get(Calendar.MONTH), dataFeriado.get(Calendar.YEAR), TipoFeriado.CORPUS_CHRISTI));		
		
		return feriados;
	}    

	/**
	 * Método retorna a data calculada do feriado, de acordo com o tipo de feriado informado,
	 * no ano em que está sendo realizada a verificação.
	 * 
	 * @param ano
	 * @param tipo
	 * @return A data daquele feriado naquele ano
	 */
	public static Calendar getDataVariavel(int ano, TipoFeriado tipo) {
		int x = -1;
		int y = -1;
		for (int i=0; i < TABELA_ANOS.length; i++) {
			if (ano >= TABELA_ANOS[i][0] && ano <= TABELA_ANOS[i][1]) {
				x = TABELA_VARIAVEIS[i][0];
				y = TABELA_VARIAVEIS[i][1];
				break;
			}
		}
		
		int a = ano % 19;
		int b = ano % 4;
		int c = ano % 7;
		int d = ((19 * a) + x) % 30;
		int e = ((2 * b) + (4 * c) + (6 * d) + y) % 7;
		
		int dia = 0;
		int mes = 0;
		if ((d + e) > 9) {
			dia = (d + e - 9);
			mes = 4;
		} else {
			dia = (d + e + 22);
			mes = 3;
		}
		
		/*
		 *  Quando o domingo de páscoa cair em Abril e o dia for 26,
		 *  altera para uma semana antes, ou seja, vai para dia 19
		 */
		if (mes == 4 && dia == 26) {
			dia = 19;
		} else
		/*
		 * Quando o domingo de Páscoa cair em Abril e o dia for 25 e o termo "d" for igual a 28,
		 * simultaneamente com "a" maior que 10, então o dia é corrigido para 18
		 */
		if (mes == 4 && dia == 25 && d == 28 && a > 10) {
			dia = 18;
		}

		Calendar calendario = Calendar.getInstance();
		
		calendario.set(ano, (mes - 1), dia);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		
		Calendar dataSelecionada = null;
		switch (tipo) {
		// Carnaval ocorre 47 dias antes da páscoa
		case CARNAVAL:
			calendario.add(Calendar.DATE, -47);
			dataSelecionada = calendario;
			break;
		// Sexta feira santa ocorre dois dias antes da páscoa
		case SEXTA_FEIRA_SANTA:
			calendario.add(Calendar.DATE, -2);
			dataSelecionada = calendario;
			break;
		// páscoa é a data base
		case PASCOA:
			dataSelecionada = calendario;
			break;
		// Corpus Christi ocorre 60 dias após a páscoa
		case CORPUS_CHRISTI:
			calendario.add(Calendar.DATE, 60);
			dataSelecionada = calendario;
			break;
		default:
			break;
		}
		
		return dataSelecionada;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public TipoFeriado getFeriado() {
		return feriado;
	}

	public void setFeriado(TipoFeriado feriado) {
		this.feriado = feriado;
	}	

	
}
