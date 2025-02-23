package com.hunger.saviour.portal.config.services;

import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.entities.UserEntity;
import com.hunger.saviour.portal.repositories.UserRepository;
import com.hunger.saviour.portal.utilities.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userInfo=userRepository.findByUsername(username);
    //  UserDetails userDetails=userInfo.get();
           return userInfo.
                   orElseThrow(() -> new UsernameNotFoundException("Username is found in database"));
    }
}
