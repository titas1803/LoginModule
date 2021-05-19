package com.cg.login.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.login.dao.ILoginDao;
import com.cg.login.dao.IUserDao;
import com.cg.login.dto.LoginDto;
import com.cg.login.entity.Login;
import com.cg.login.entity.User;
import com.cg.login.exceptions.LoginException;
import com.cg.login.exceptions.UserNotFoundException;
import com.cg.login.util.LoginConstants;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private IUserDao userdao;
	@Autowired
	private ILoginDao logindao;

	Logger logger=LoggerFactory.getLogger(LoginServiceImpl.class);
	
	public Map<String, Login> authMap = new HashMap<>();

	@Override
	public Login doLogin(Integer userId, String password) throws LoginException {
		Login login=null;
		logger.debug("doing login process");
		Optional<Login> optLogin=logindao.findById(userId);
		if(!optLogin.isPresent())
			throw new LoginException(LoginConstants.CHECK_YOUR_CREDENTIALS);
		login=optLogin.get();
		if(!login.getPassword().contentEquals(encryptString(password)))
			throw new LoginException(LoginConstants.PASSWORD_WRONG);
		return login;
	}

	public String generateToken(Login login) {
		String token=encryptLogin(login);
		authMap.put(token, login);
		return token;
	}
	
	

	public Map<String, Login> getAuthMap() {
		return authMap;
	}

	@Override
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] + 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	@Override
	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] - 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	@Override
	public String encryptLogin(Login loginAcnt) {
		return encryptString(loginAcnt.getUserId().toString()) + "-" + encryptString(loginAcnt.getRole());
	}

	public boolean verifyLogin(String tokenId) throws LoginException
	{
		if(!authMap.containsKey(tokenId)) {
			throw new LoginException(LoginConstants.INVALID_LOGIN_TOKEN);
		}
		return true;
	}

	@Override
	public boolean isAdmin(String tokenid) {
		if(authMap.get(tokenid).getRole().equals("admin"))
			return true;
		return false;
	}

}
