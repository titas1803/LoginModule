package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.login.dao.ILoginDao;
import com.cg.login.dao.IUserDao;
import com.cg.login.dto.UserDto;
import com.cg.login.entity.Login;
import com.cg.login.entity.User;
import com.cg.login.service.ILoginService;
import com.cg.login.service.IUserService;
import com.cg.login.service.LoginServiceImpl;
import com.cg.login.service.UserServiceImpl;

@SpringBootTest
public class TestCreateUser {

	@Mock
	private ILoginDao logindao;
	@Mock
	private IUserDao userdao;
	@InjectMocks
	private IUserService userSer=new UserServiceImpl();
	@InjectMocks
	private ILoginService  loginSer=new LoginServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		User persistedUser=new User(1001, "abcd", "1234567890", "abcd@ghf.com", LocalDate.of(2020, 12, 30), "Sodepur", "kolkata");
		Login persistedLogin= new Login(1001, "Abcd@123", "user");
		when(userdao.save(any(User.class))).thenReturn(persistedUser);
		when(logindao.save(any(Login.class))).thenReturn(persistedLogin);
	}
	
	@Test
	@DisplayName(value = "testCreateUser 1")
	public void testCreateUser()
	{
		UserDto userdto=new UserDto(1001, "abcd", "1234567890", "abcd@ghf.com", LocalDate.of(2020, 12, 30), "sodepur", "kolkata", "Abcd@123", "user");
		assertEquals(userSer.createUser(userdto), 1001);
	}
	
}
