package com.config.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.config.dao.UserRepository;
import com.config.entities.User;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//loadUserByUsername ke use se Ham check krenge ki wo user database m available hai ya nahi
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	
		User user = userRepository.findByUserName(userName);
		if	(user==null) {
			throw new UsernameNotFoundException("user name not found");
		}
		else {
			return new CustomUser(user); //If Id exist then return the CustomUser object with user details.
		}
	}
}