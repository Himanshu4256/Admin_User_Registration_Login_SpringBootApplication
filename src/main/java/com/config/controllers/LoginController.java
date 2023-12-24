package com.config.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.config.entities.User;
import com.config.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String showLoginForm() {
		return "Login";
	}

//	@PostMapping("/logins")
//	public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String plainPassword,User users) {
//		User user = loginService.login(userName, plainPassword);
//		
//		if (user != null) {
//			// Successful login
//			return "AdminDashboard";
//		} else {
//			System.out.println("hello Login failed");
//			return "Login";
//		}
//	}
}