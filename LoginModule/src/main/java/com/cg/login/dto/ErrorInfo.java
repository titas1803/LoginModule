package com.cg.login.dto;

import java.util.List;

public class ErrorInfo {

	private String message;
	private String code;
	private List<String> messages;
	
	
	public ErrorInfo(String code, String message) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public ErrorInfo(String code, List<String> messages) {
		super();
		this.code = code;
		this.messages = messages;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
