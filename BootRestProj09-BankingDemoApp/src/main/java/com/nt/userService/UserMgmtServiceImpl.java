package com.nt.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.userEntity.User;
import com.nt.userRepo.UserRepository;
import com.nt.userdto.UserDTO;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		
		User user = new User();
		
		user.setAddress(userDTO.getAddress());
		user.setDob(userDTO.getDob());
		user.setFullName(userDTO.getFullName());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setUsername(userDTO.getUsername());
		user.setRole(userDTO.getRole());
		
		User saveUser = userRepo.save(user);
		
		UserDTO dto = convertUserToDTO(user);
		return dto;
	}

	private UserDTO convertUserToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setAddress(user.getAddress());
		dto.setDob(user.getDob());
		dto.setFullName(user.getFullName());
		dto.setPassword(user.getPassword());
		dto.setUid(user.getUid());
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole());
		return dto;
	}

	@Override
	public boolean isUsernameExists(String username) {
		  return userRepo.existsByUsername(username);
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> opt = userRepo.findByUsername(username);
		
		if(opt.isPresent()) {
			User user = opt.get();
			return user;
		}
		throw new UsernameNotFoundException("Username not found");
	}

}
