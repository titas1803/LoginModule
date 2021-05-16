package com.cg.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.login.entity.User;

public interface IUserDao extends JpaRepository<User, Integer> {

}
