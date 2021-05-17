package com.cg.login.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDto {

	@NotNull(message="User Id Cannot be null")
	private Integer userId;
	
	@NotBlank(message = "password required")
	private String password;
	
	private String role;

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
