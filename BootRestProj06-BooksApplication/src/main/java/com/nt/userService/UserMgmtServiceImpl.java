package com.nt.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.userEntity.UserEntity;
import com.nt.userRepo.IUserRepository;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public String registerUser(UserEntity userEntity) {
		userEntity.setPassword(encoder.encode(userEntity.getPassword()));
		int id = userRepo.save(userEntity).getId();
		return "user registered successfully with id value"+" 		```"+id;
	}

	@Override
	public UserEntity getUserById(Integer id) {
		Optional<UserEntity> opt = userRepo.findById(id);
		UserEntity user = new UserEntity();
		
		if(opt.isPresent()) {
			user = opt.get();
		}
		throw new IllegalArgumentException("user id not found");
	}

	@Override
	public UserEntity getUserByUsername(String name) {
		 
		UserEntity user =  userRepo.findUserByUsername(name);
		return user;
	}

	@Override
	public UserEntity getUserByName(String name) {
		UserEntity user =  userRepo.findUserByName(name);
		return user;
	}
	


}
