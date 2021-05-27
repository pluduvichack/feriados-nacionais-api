package com.feriados.nacionais.service;

import com.feriados.nacionais.dto.ResultadoDto;
import com.feriados.nacionais.model.FeriadoNacional;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class FeriadoService {

	public ResultadoDto verificaDataEhFeriado(Calendar data) {
		//cria a lista com os feriados fixos
		List<FeriadoNacional> feriadosNacionais = FeriadoNacional.getListaFeriadosNacionais();
		
		//adiciona à lista os feriados variáveis do ano da pesquisa
		feriadosNacionais.addAll(FeriadoNacional.getFeriadosBaseadosNoCarnaval(data.get(Calendar.YEAR)));
		
		return this.verificaFeriadoNacionalFixo(data, feriadosNacionais);
	}
	
	private ResultadoDto verificaFeriadoNacionalFixo(Calendar data, List<FeriadoNacional> feriadosNacionais) {
		
		Optional<FeriadoNacional> feriado = feriadosNacionais.stream().filter(it -> 
				this.comparaDias(data.get(Calendar.DAY_OF_MONTH), it.getDia()) &&
				this.comparaMeses(data.get(Calendar.MONTH), it.getMes()) &&
				this.comparaAnos(data.get(Calendar.YEAR), it.getAno())).findFirst();
		
		return this.criaMensagemRetorno(feriado);
		
	}
	
	private boolean comparaDias(int diaConsulta, int diaFeriado) {
		return diaConsulta == diaFeriado;
	}
	
	private boolean comparaMeses(int mesConsulta, int mesFeriado) {
		return mesConsulta == mesFeriado;
	}
	
	private boolean comparaAnos(int anoConsulta, int anoFeriado) {
		//se for 0, é um feriado fixo, então, não importa o ano
		if(anoFeriado == 0) {
			return true;
		}
		
		return anoConsulta == anoFeriado;
	}
	
	private ResultadoDto criaMensagemRetorno(Optional<FeriadoNacional> feriado) {
		ResultadoDto dto = new ResultadoDto();
		
		if(feriado.isPresent()) {
			dto.setFeriado(true);
			dto.setDescricao(feriado.get().getFeriado().getNome());
		} else {
			dto.setFeriado(false);
		}
		
		return dto;
	}
	
	
}
