package com.cg.login.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.login.dto.SuccessMessage;
import com.cg.login.dto.UserDto;
import com.cg.login.entity.User;
import com.cg.login.exceptions.LoginException;
import com.cg.login.exceptions.UserNotFoundException;
import com.cg.login.exceptions.ValidateUserException;
import com.cg.login.service.ILoginService;
import com.cg.login.service.IUserService;
import com.cg.login.util.LoginConstants;

@RestController
public class UserRestController {

	@Autowired
	private IUserService userSer;

	@Autowired
	private ILoginService loginSer;

	@PostMapping("createuser")
	public SuccessMessage createUserRegistration(@Valid @RequestBody UserDto userdto, BindingResult br)
			throws ValidateUserException {
		if (br.hasErrors())
			throw new ValidateUserException(br.getFieldErrors());
		Integer userId = userSer.createUser(userdto);
		return new SuccessMessage(LoginConstants.USER_CREATED);
	}

	@GetMapping("viewusers")
	public List<User> viewUsers(@RequestHeader("token-id") String tokenId)
			throws LoginException, UserNotFoundException {
		if (loginSer.verifyLogin(tokenId))
			return userSer.viewAllUser();
		throw new LoginException(LoginConstants.INVALID_LOGIN_TOKEN);
	}
//	@RequestHeader("token-id") String tokenId

	@GetMapping("viewbylocation/{location}")
	public List<User> viewByLocation(@PathVariable("location") String location,
			@RequestHeader("token-id") String tokenId) throws LoginException, UserNotFoundException {
		if (loginSer.verifyLogin(tokenId))
			return userSer.viewByLocation(location);
		throw new LoginException(LoginConstants.INVALID_LOCATION);
	}

}
