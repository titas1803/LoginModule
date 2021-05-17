package com.cg.login.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

public class UserDto {
	
//	private Integer userId;
	
	@NotBlank(message = "userName must not be blank")
	private String userName;
	
	@NotBlank(message = "COntact must not be blank")
	@Pattern(regexp = "[0-9]{10}")
	private String contactNo;
	
	@NotBlank(message = "emailId must not be blank")
	@Email(message = "Invalid email")
	private String emailId;
	
	@Past(message = "DOB must be past date")
	private LocalDate userDob;
	
	@NotBlank(message = "User address must not be blank")
	private String userAddress;
	
	@NotBlank(message = "location must not be blank")
	private String location;
	
	private String password;
	
	@NotBlank(message = "role can't be blank")
	@Pattern(regexp="(user|admin)")
	private String role;
	
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

	public UserDto() {
		
	}

//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getUserDob() {
		return userDob;
	}

	public void setUserDob(LocalDate userDob) {
		this.userDob = userDob;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
