package com.cg.login.service;

public interface ILoginService {
	public Integer createLoginAccount(Integer userId, String password, String role);
	public String encryptString(String str);
	public String decryptString(String str);

}
