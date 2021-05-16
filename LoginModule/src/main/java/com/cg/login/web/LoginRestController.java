package com.cg.login.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.login.entity.Login;
import com.cg.login.exceptions.LoginException;
import com.cg.login.exceptions.UserNotFoundException;
import com.cg.login.service.ILoginService;

@RestController
public class LoginRestController {

	@Autowired
	private ILoginService service;
	
	private Map<String, Login> authMap= new HashMap<>();

	@PostMapping("createlogin")
	public Integer createLogin(@RequestParam("userid") Integer userId, @RequestParam("password") String password, @RequestParam("confirmpassword") String confirmPassword, @RequestParam("role") String role ) throws LoginException, UserNotFoundException {
		
		if(!password.equals(confirmPassword))
		{
			throw new LoginException("Password and Confirm Password doesn't Match");
		}
		return service.createLoginAccount(userId, password, role);
		
	}

}
