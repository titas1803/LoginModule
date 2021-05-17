package com.cg.login.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.login.dto.UserDto;
import com.cg.login.entity.User;
import com.cg.login.exceptions.UserNotFoundException;

public interface IUserService {

	public Integer createUser(UserDto userdto);
	public List<User> viewAllUser() throws UserNotFoundException;
	public User viewUserById(int userId) throws UserNotFoundException;
	
}
