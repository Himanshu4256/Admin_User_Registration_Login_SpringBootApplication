package com.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.config.dao.RegisterRepository;
import com.config.entities.User;

@Service
public class RegisterService {
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		String encodedPassword = BCrypt.e(user.getPassword());  //password encryption using hashing
//        user.setPassword(encodedPassword);
		System.out.println("User details before saving ::: "+user);
		return  registerRepository.save(user);
	}
}