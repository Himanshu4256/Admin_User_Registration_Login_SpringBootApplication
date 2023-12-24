package com.config.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.config.entities.User;

public interface LoginRepository extends JpaRepository<User, Long> {

	User findByuserName(String userName);
}
