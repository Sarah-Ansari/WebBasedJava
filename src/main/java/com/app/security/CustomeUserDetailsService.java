package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.UserEntity;
import com.app.repository.CustDetlRepo;

//user details service to provider user details lifted from DB
@Service
@Transactional
public class CustomeUserDetailsService implements UserDetailsService {
	@Autowired
	private CustDetlRepo custDetlRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		// get user details by email, using UserEntityRepo.
		UserEntity user = custDetlRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email does not exists!!!"));
		//uer :persistent -->map it to UserDetails instance
		return new CustomeUserDetails(user);
	}

}
