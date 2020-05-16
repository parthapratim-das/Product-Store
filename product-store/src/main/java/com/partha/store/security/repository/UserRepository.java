package com.partha.store.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.partha.store.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//@Query(value = "SELECT * FROM USERS WHERE username = ?" , nativeQuery = true)
	//User findByUserName(String username);
	
	Optional<User> findByUserName(String userName);

}
