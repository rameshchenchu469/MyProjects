package com.nt.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.UserEntity.User;
import com.nt.config.JwtProvider;
import com.nt.exception.UserException;
import com.nt.userRepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			user.get();
		}
		throw new UserException("User Not Found With Id"+userId);
	}

	@Override
	public User findUserByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepo.findByEmail(email);
		
		
		return null;
	}

}
