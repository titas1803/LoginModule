package com.cg.login.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.login.entity.User;

public interface IUserService {

	public Integer createUser(String userName, String contactNo, String emailId, LocalDate userDob
			, String userAddress, String location);
	public List<User> viewAllUser();
	public User viewUserById(int userId);
}
