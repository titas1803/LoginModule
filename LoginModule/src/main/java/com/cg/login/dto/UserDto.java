package com.cg.login.dto;

import java.time.LocalDate;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

public class UserDto {
	
	private Integer userId;
	
	@NotBlank(message = "userName must not be blank")
	private String userName;
	
	
	private String contactNo;
	
	@NotBlank(message = "emailId must not be blank")
	private String emailId;
	
	@Past(message = "DOB must be past date")
	private LocalDate userDob;
	
	@NotBlank(message = "User address must not be blank")
	private String userAddress;
	
	@NotBlank(message = "location must not be blank")
	private String location;
	
	public UserDto() {
		
	}

	public UserDto(Integer userId, String userName, String contactNo, String emailId, LocalDate userDob,
			String userAddress, String location) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.userDob = userDob;
		this.userAddress = userAddress;
		this.location = location;
	}

	public UserDto(String userName, String contactNo, String emailId, LocalDate userDob, String userAddress,
			String location) {
		super();
		this.userName = userName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.userDob = userDob;
		this.userAddress = userAddress;
		this.location = location;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
