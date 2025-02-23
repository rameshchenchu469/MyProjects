package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.dto.UserDTO;
import com.nt.entity.User;
import com.nt.reposiroty.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private IUserRepository userRepo;

	@Override
	public String registerUser(UserDTO userDTO) {
		User user = new User();
	
		user.setContactNo(userDTO.getContactNo());
		user.setUsername(userDTO.getUsername());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		user.setEmail(userDTO.getEmail());
		user.setRelatedId(userDTO.getRelatedId());
		
		User user1 = userRepo.save(user);
		return "user registered successfully with id value:"+user1.getUserId();
	}

	@Override
	public User getUserByUsername(String username) {
		User user =  userRepo.findUserByUsername(username);
		return user;
	}

}
