package com.config.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.config.entities.User;

public interface RegisterRepository extends JpaRepository<User, Long> {

}
