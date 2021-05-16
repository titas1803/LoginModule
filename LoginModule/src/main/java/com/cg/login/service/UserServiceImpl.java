package com.cg.login.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cg.login.dao.IUserDao;
import com.cg.login.entity.User;

public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userdao;

	@Override
	@Transactional
	public Integer createUser(String userName, String contactNo, String emailId, LocalDate userDob, String userAddress,
			String location) {
		
		User user=new User();
		user.setUserName(userName);
		user.setContactNo(contactNo);
		user.setEmailId(emailId);
		user.setUserdob(userDob);
		user.setUseraddress(userAddress);
		user.setLocation(location);
		User persistedUser=userdao.save(user);
		
		
		return persistedUser.getUserId();
	}

	@Override
	public List<User> viewAllUser() {
		List<User> lst=userdao.findAll();
		if(lst.isEmpty())
		{
			
		}
		lst.sort((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()));
		return lst;
	}

	@Override
	public User viewUserById(int userId) {
		Optional<User> user=userdao.findById(userId);
		if(!user.isPresent())
		{
			
		}
		
		return user.get();
	}

}
