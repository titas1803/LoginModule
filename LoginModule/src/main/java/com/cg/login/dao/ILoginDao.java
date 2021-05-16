package com.cg.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.login.entity.Login;

public interface ILoginDao extends JpaRepository<Login, Integer> {

}
