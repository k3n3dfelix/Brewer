package com.algaworks.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;

import com.algaworks.brewer.model.Estilo;

public class EstiloConverter implements Converter <String, Estilo> {
	
	@Override
	public Estilo convert(String codigo){
		if(codigo != null && !codigo.trim().equals("")){ // ou if(!StringUtils.isEmpty(codigo))
		Estilo estilo = new Estilo();
		estilo.setCodigo(Long.valueOf(codigo));
		return estilo;
		}
		return null;
	}
}
