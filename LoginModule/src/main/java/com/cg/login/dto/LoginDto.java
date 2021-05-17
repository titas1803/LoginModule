package com.cg.login.dto;

public class LoginDto {

	private Integer userId;
	
	private String password;
	
	private String role;

	public LoginDto(Integer userId, String password, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	public LoginDto() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
