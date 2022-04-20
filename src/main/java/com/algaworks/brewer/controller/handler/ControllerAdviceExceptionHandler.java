package com.algaworks.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.brewer.service.exception.NomeEstiloCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeEstiloCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloCadastradoException(NomeEstiloCadastradoException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
