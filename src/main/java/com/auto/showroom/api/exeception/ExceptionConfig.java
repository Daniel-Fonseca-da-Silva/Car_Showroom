package com.auto.showroom.api.exeception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auto.showroom.domain.dto.CarDTO;

@RestControllerAdvice
public class ExceptionConfig {

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<CarDTO> errorNotFound(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler( {IllegalArgumentException.class} )
	public ResponseEntity<CarDTO> errorBadRequest(Exception e) {
		return ResponseEntity.badRequest().build();
	}

}
