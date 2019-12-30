package com.mc.modelagem.exceptions;

public class ServiceConstraintViolationException  extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public ServiceConstraintViolationException(String message) {
		super(message);
	}
	
	public ServiceConstraintViolationException(String message, Throwable cause) {
		super(message,cause);
	}

}
