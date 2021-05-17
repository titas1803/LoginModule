package com.cg.login.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.login.dao.IUserDao;
import com.cg.login.dto.UserDto;
import com.cg.login.entity.User;
import com.cg.login.exceptions.UserNotFoundException;

@Service("myser")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userdao;

	@Override
	@Transactional
	public Integer createUser(UserDto userdto) {
		
		User user=new User();
		user.setUserName(userdto.getUserName());
		user.setContactNo(userdto.getContactNo());
		user.setEmailId(userdto.getEmailId());
		user.setUserdob(userdto.getUserDob());
		user.setUseraddress(userdto.getUserAddress());
		user.setLocation(userdto.getLocation());
		User persistedUser=userdao.save(user);
		
		return persistedUser.getUserId();
	}

/*	@Override
	public List<User> viewAllUser() throws UserNotFoundException {
		List<User> lst=userdao.findAll();
		if(lst.isEmpty())
		{
			throw new UserNotFoundException("No User Found");
		}
		lst.sort((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()));
		return lst;
	}

	@Override
	public User viewUserById(int userId) throws UserNotFoundException {
		Optional<User> user=userdao.findById(userId);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User Not Found For Id: "+userId);
		}
		
		return user.get();
	}*/

}
