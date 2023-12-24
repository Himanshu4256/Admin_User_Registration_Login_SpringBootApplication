package com.config.logintest;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.config.dao.LoginRepository;
import com.config.entities.User;
import com.config.service.LoginService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class LoginServiceTest {
	
	@Mock
	private LoginRepository loginRepository;

	@InjectMocks
	private LoginService loginService;
	
//	@Test
//	public void loginTest() {
//		when(loginRepository.findByuserName(ArgumentMatchers.anyString())).thenReturn(loginDetails());
//		User login = loginService.login("A", "1234");
//		assertEquals(login, login);
//		
//	}
//	
//	private User loginDetails() {
//		User user = new User();
//		user.setCompany("Optum");
//		user.setDesignation("Software Developer");
//		user.setEmail("himanshu@optum.com");
//		user.setPassword("Jan@2024!!");
//		user.setRole("Admin");
//		return user;
//	}
}
