package com.partha.store;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.partha.store.models.DBUserDetails;
import com.partha.store.models.User;
import com.partha.store.security.repository.UserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepository.findByUserName(userName);
		
		userEntity.orElseThrow(() -> new UsernameNotFoundException("not found "+userName));
		User user = userEntity.get();
		DBUserDetails myUserDetails = new DBUserDetails(user);
		
		return myUserDetails;
	}

}
