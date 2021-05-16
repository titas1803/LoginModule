package com.cg.login.service;

import com.cg.login.exceptions.UserNotFoundException;

public interface ILoginService {
	public Integer createLoginAccount(Integer userId, String password, String role) throws UserNotFoundException;
	public String encryptString(String str);
	public String decryptString(String str);

}
