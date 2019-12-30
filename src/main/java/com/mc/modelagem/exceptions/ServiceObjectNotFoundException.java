package com.mc.modelagem.exceptions;

public class ServiceObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ServiceObjectNotFoundException(String message) {
		super(message);
	}
	
	public ServiceObjectNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}

}
