package com.cg.login.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.login.dto.LoginDto;
import com.cg.login.entity.Login;
import com.cg.login.exceptions.LoginException;
import com.cg.login.exceptions.UserNotFoundException;
import com.cg.login.service.ILoginService;

@RestController
public class LoginRestController {

	@Autowired
	private ILoginService service;
	
	Logger logger=LoggerFactory.getLogger(LoginRestController.class);

	private Map<String, Login> authMap = new HashMap<>();

	@PostMapping("createlogin")
	public Integer createLogincontroller(@RequestParam("userid") Integer userId, @RequestParam("password") String password,
			@RequestParam("confirmpassword") String confirmPassword, @RequestParam("role") String role)
			throws LoginException, UserNotFoundException {

		if (!password.contentEquals(confirmPassword)) {
			throw new LoginException("Password and Confirm Password doesn't Match");
		}
		LoginDto logindto = new LoginDto(userId, password, role);
		return service.createLoginAccount(logindto);
	}
	
	@PostMapping("login")
	public String doLoginController(@RequestParam("userid") Integer userId, @RequestParam("password") String password) throws LoginException
	{
		logger.info("User Id "+userId);
		logger.debug("UserId recieved");
		Login login=service.doLogin(userId, password);
		String token=service.encryptLogin(login);
		authMap.put(token, login);
		return token;
	}

}
