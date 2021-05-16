package com.cg.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.login.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

}
