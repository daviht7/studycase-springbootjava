package com.mc.modelagem.resources;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mc.modelagem.domain.StandardError;
import com.mc.modelagem.exceptions.ServiceConstraintViolationException;
import com.mc.modelagem.exceptions.ServiceObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandle {
	
	@ExceptionHandler(ServiceObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ServiceObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(ServiceConstraintViolationException.class)
	public ResponseEntity<StandardError> contraintViolation(ServiceConstraintViolationException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	

}
