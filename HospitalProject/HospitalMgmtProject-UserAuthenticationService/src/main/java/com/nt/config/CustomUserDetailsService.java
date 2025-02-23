package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nt.reposiroty.IUserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByUsername(username);
	}

}
