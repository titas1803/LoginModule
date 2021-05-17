 package com.cg.login.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.login.dto.ErrorMessage;
import com.cg.login.exceptions.*;

@RestControllerAdvice
public class UserAdvice {
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionUserNotFound(UserNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
//	@ExceptionHandler(LoginException.class)
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex)	{
		if(ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid date pattern");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");
	}
	
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex)	{
		if(ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid date pattern , follow yyyy-MM-dd");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");
	}
	
	@ExceptionHandler(ValidateUserException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException2(ValidateUserException ex) {
		List<String> errors=ex.getErrors().stream()
				.map(err-> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}
	
//	public ErrorMessage handleException2(DeptException ex) {
//		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
//	}

}
