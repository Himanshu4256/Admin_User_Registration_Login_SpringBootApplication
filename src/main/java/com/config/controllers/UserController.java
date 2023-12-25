 package com.config.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.entities.User;
import com.config.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		return ResponseEntity.ok("your department is at ID :" + savedUser.getUserId());
    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/Update/{id}")
	public ResponseEntity<?> updateUserById(@Valid @PathVariable("id") Long uId,@RequestBody User u){
		User updateUser = userService.updateUser(uId, u);
		return ResponseEntity.ok("Your data is updated of Id :" +updateUser.getUserId());
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
		userService.deleteUserDetails(id);
		return ResponseEntity.ok("Your User is deleted");
	}
	
	@GetMapping("/userProfile")
	public String profile(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		
		return "UserProfile";
	}
	
	
}