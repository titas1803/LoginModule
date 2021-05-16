package com.cg.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.login.dao.ILoginDao;
import com.cg.login.dao.IUserDao;
import com.cg.login.entity.Login;
import com.cg.login.entity.User;

public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private IUserDao userdao; 
	@Autowired
	private ILoginDao logindao;

	@Override
	public Integer createLoginAccount(Integer userId, String password, String role) {
		Optional<User> user= userdao.findById(userId);
		if(!user.isPresent())
		{
			
		}
		Login login=new Login();
		login.setUserId(userId);
		login.setPassword(password);
		login.setRole(role);
		Login persistedLogin= logindao.save(login);
		return persistedLogin.getUserId();
	}

	@Override
	public String encryptString(String str) {
		// TODO Auto-generated method stub
		char[] arr= str.toCharArray();
		StringBuffer sb= new StringBuffer();
		int ch;
		for(int idx=0; idx<arr.length; idx++)
		{
			ch=arr[idx]+3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	@Override
	public String decryptString(String str) {
		// TODO Auto-generated method stub
		char[] arr= str.toCharArray();
		StringBuffer sb= new StringBuffer();
		int ch;
		for(int idx=0; idx<arr.length; idx++)
		{
			ch=arr[idx]-3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

}
