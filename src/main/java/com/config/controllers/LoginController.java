package com.config.controllers;

import java.util.Optional;

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

import com.config.dao.UserRepository;
import com.config.entities.User;
import com.config.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String showLoginForm() {
		return "Login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String plainPassword,Model model) {
		User user = loginService.login(userName, plainPassword);
		 model.addAttribute("user", user);
		
		 if (user != null) {
		        if ("ROLE_ADMIN".equals(user.getRole())) {
		            // Successful login for admin
		            return "AdminDashboard";
		        } else if ("ROLE_USER".equals(user.getRole())) {
		            // Successful login for user
		            return "UserProfile";
		        }
		    }
		    System.out.println("Login failed");
		    return "Login";
	}

}