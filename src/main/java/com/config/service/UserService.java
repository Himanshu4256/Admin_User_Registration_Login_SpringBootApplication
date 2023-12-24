package com.config.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.config.advice.BaseResponse;
import com.config.dao.UserRepository;
import com.config.entities.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public ResponseEntity<?> findUserById(Long id) {
		BaseResponse<User> response = new BaseResponse();
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			response.setMessage("Data Found Successfully");
			response.setStatus("200");
			response.setData(user);
		}
		 
		return new ResponseEntity<>(response, HttpStatus.OK);
}
	
	
	public User updateUser(Long id, User u) {
		 Optional<User> findIdOptional = userRepository.findById(id);
		 u.setUserId(id);
		 User user = null;
		 if (findIdOptional != null) {
			 user = userRepository.save(u);
		}
		 return user;
	 }
	
	
	 public void deleteUserDetails(Long id) {
		 if (id <= 0) {
			throw new IllegalArgumentException("Invalid user ID");
		}
		 userRepository.deleteById(id);
	 }
}
