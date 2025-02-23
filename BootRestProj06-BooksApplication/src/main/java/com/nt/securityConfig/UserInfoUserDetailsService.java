package com.nt.securityConfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nt.userEntity.UserEntity;
import com.nt.userEntity.UserInfoUserDetails;
import com.nt.userRepo.IUserRepository;



@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	Optional<UserEntity> userInfo=userRepo.findByUsername(username);
		 return userInfo.map(UserInfoUserDetails::new)
				 .orElseThrow(()-> new UsernameNotFoundException(username+"user not found") );
	}

}
