package com.mc.modelagem.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.mc.modelagem.domain.StandardError;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> lista = new ArrayList<FieldMessage>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return lista;
	}

	public void addError(String fieldName, String message) {
		lista.add(new FieldMessage(fieldName,message));
	}

}
