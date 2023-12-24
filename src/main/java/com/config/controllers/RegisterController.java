package com.config.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.config.entities.User;
import com.config.service.RegisterService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
//	@RequestMapping("/welcome")
//	public String register(HttpServletRequest request) {
//		request.setAttribute("mode", "MODE_HOME");
//		return "Welcome";
//	}
	
	@RequestMapping("/userDetails")
	public String userInfo(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "Register";
	}
	
	@PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, Model model) {
		 registerService.saveUser(user);
		 model.addAttribute("successMsg", "Your Registration is Done");
	     return "Register"; 
    }
}