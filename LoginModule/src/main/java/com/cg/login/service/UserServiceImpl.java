package com.cg.login.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.login.dao.ILoginDao;
import com.cg.login.dao.IUserDao;
import com.cg.login.dto.UserDto;
import com.cg.login.entity.Login;
import com.cg.login.entity.User;
import com.cg.login.exceptions.UserNotFoundException;
import com.cg.login.util.LoginConstants;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userdao;

	@Autowired
	private ILoginService loginSer;

	@Autowired
	private ILoginDao logindao;
	
	@Autowired
	private IUserService userSer;
	

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public Integer createUser(UserDto userdto) {

		User user = new User();
		user.setUserName(userdto.getUserName().toLowerCase());
		user.setContactNo(userdto.getContactNo());
		user.setEmailId(userdto.getEmailId());
		user.setUserdob(userdto.getUserDob());
		user.setUseraddress(userdto.getUserAddress());
		user.setLocation(userdto.getLocation().toLowerCase());
		User persistedUser = userdao.save(user);
		userdao.flush();
		Login login = new Login();
		login.setPassword(loginSer.encryptString(userdto.getPassword()));
		login.setRole(userdto.getRole());
		login.setUser(persistedUser);
		logindao.save(login);
		return persistedUser.getUserId();
	}

	@Override
	public List<User> viewAllUser() throws UserNotFoundException {
		List<User> lst = userdao.findAll();
		if (lst.isEmpty()) {
			throw new UserNotFoundException(LoginConstants.NO_USER_FOUND);
		}
		lst.sort((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()));
		return lst;
	}

	@Override
	public List<User> viewByLocation(String location) throws UserNotFoundException {
		List<User> lst=userdao.findByLocation(location.toLowerCase());
		if(lst.isEmpty())
			throw new UserNotFoundException(LoginConstants.NO_USER_FOUND);
		lst.sort((u1,u2)-> u1.getUserName().compareTo(u2.getUserName()));
		return lst;
	}

	@Override
	public List<User> viewByName(String userName) throws UserNotFoundException {
		List<User> lst=userdao.findByName(userName.toLowerCase());
		if(lst.isEmpty())
			throw new UserNotFoundException(LoginConstants.NO_USER_FOUND);
		lst.sort((u1,u2)-> u1.getUserName().compareTo(u2.getUserName()));
		return lst;
	}



	/*
	 * @Override public User viewUserById(int userId) throws UserNotFoundException {
	 * Optional<User> user=userdao.findById(userId); if(!user.isPresent()) { throw
	 * new UserNotFoundException("User Not Found For Id: "+userId); }
	 * 
	 * return user.get(); }
	 */


}
