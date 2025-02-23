package com.nt.userService;

import com.nt.userEntity.UserEntity;

public interface IUserMgmtService {

	public String registerUser(UserEntity userEntity);
	public UserEntity getUserById(Integer id);
	public UserEntity getUserByUsername(String name);
	public UserEntity getUserByName(String name);
	
}
