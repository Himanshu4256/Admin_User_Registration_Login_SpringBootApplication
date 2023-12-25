package com.config.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") User user) {
		return "Register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		registerService.saveUser(user);
		model.addAttribute("message", "Registration Successfully");
		return "Register";
	}
}