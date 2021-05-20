package com.cg.login.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.login.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {
	@Query("from User u where u.location = :location")
	public List<User> findByLocation(@Param("location") String location);
	
	@Query("from User u where u.userName like %:username%")
	public List<User> findByName(@Param("username") String userName);

}
