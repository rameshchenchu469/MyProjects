package com.nt.userService;

import com.nt.UserEntity.User;
import com.nt.exception.UserException;

public interface UserService {

	public User findUserById(Long id)throws UserException;
	public User findUserByJwt(String jwt)throws UserException;
}
