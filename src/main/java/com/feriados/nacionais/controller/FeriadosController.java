package com.feriados.nacionais.controller;

import com.feriados.nacionais.dto.ResultadoDto;
import com.feriados.nacionais.service.FeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/feriados")
public class FeriadosController {


	@Autowired
	private FeriadoService feriadoService;
	
	@GetMapping("/{data}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResultadoDto verificaFeriado(@PathVariable @DateTimeFormat(pattern =  "dd-MM-yyyy") Calendar data) {
		return this.feriadoService.verificaDataEhFeriado(data);
	}
	
}
