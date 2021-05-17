package com.cg.login.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateUserException extends Exception{

	private List<FieldError> errors;
	
	public ValidateUserException() {
		super();

	}

	public ValidateUserException(String arg0) {
		super(arg0);
	}
	
	public ValidateUserException(List<FieldError> errors) {
		this.errors=errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	

}
