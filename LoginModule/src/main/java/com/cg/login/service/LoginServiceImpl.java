package com.cg.login.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.login.dao.ILoginDao;
import com.cg.login.dao.IUserDao;
import com.cg.login.dto.LoginDto;
import com.cg.login.entity.Login;
import com.cg.login.entity.User;
import com.cg.login.exceptions.LoginException;
import com.cg.login.exceptions.UserNotFoundException;

@Service("loginservice")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private IUserDao userdao;
	@Autowired
	private ILoginDao logindao;

	Logger logger=LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	@Transactional
	public Integer createLoginAccount(LoginDto logindto) throws UserNotFoundException {
		Optional<User> user = userdao.findById(logindto.getUserId());
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found For Id: " + logindto.getUserId());
		}
		Login login = new Login();
		login.setUserId(logindto.getUserId());
		login.setPassword(encryptString(logindto.getPassword()));
		login.setRole(logindto.getRole());
		Login persistedLogin = logindao.save(login);
		return persistedLogin.getUserId();
	}

	@Override
	public Login doLogin(Integer userId, String password) throws LoginException {
		Login login=null;
		logger.debug("doing login process");
		Optional<Login> optLogin=logindao.findById(userId);
		if(!optLogin.isPresent())
			throw new LoginException("Check your credential");
		login=optLogin.get();
		if(!login.getPassword().contentEquals(decryptString(password)))
			throw new LoginException("Password is Wrong, Check your password");
		logger.info("user logged in for userid "+userId);
		return login;
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


}
