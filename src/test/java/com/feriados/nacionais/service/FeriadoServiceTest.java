package com.feriados.nacionais.service;

import com.feriados.nacionais.dto.ResultadoDto;
import com.feriados.nacionais.model.TipoFeriado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FeriadoServiceTest {

	
	@Autowired
	private FeriadoService feriadoService;
	
	@Test
	public void deveRetornarFeriadoAnoNovo() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(1, Calendar.JANUARY, 2021));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.CONFRATERNIZACAO_UNIVERSAL.getNome(), dto.getDescricao());
	}
	
	@Test
	public void deveRetornarFeriadoTiradentes() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(21, Calendar.APRIL, 2021));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.TIRADENTES.getNome(), dto.getDescricao());
	}

	@Test
	public void deveRetornarFeriadoCarnaval1958() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(18, Calendar.FEBRUARY, 1958));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.CARNAVAL.getNome(), dto.getDescricao());
	}	
	
	@Test
	public void deveRetornarFeriadoPascoa1958() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(06, Calendar.APRIL, 1958));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.PASCOA.getNome(), dto.getDescricao());
	}		

	@Test
	public void deveRetornarFeriadoCorpusChristi1958() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(05, Calendar.JUNE, 1958));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.CORPUS_CHRISTI.getNome(), dto.getDescricao());
	}		
	
	@Test
	public void deveRetornarFeriadoCarnaval2078() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(15, Calendar.FEBRUARY, 2078));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.CARNAVAL.getNome(), dto.getDescricao());
	}	
	
	@Test
	public void deveRetornarFeriadoPascoa2078() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(03, Calendar.APRIL, 2078));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.PASCOA.getNome(), dto.getDescricao());
	}		

	@Test
	public void deveRetornarFeriadoCorpusChristi2078() {
		ResultadoDto dto = feriadoService.verificaDataEhFeriado(this.criaData(02, Calendar.JUNE, 2078));
		
		assertTrue(dto.isFeriado());
		assertEquals(TipoFeriado.CORPUS_CHRISTI.getNome(), dto.getDescricao());
	}	
	
	private Calendar criaData(int dia, int mes, int ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		
		return cal;
		
	}
}
