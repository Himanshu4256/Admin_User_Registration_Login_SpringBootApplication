package com.config.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.config.entities.User;
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUserName(String userName);
}
