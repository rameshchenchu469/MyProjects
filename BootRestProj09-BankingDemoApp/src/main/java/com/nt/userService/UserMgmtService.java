package com.nt.userService;


import com.nt.userEntity.User;
import com.nt.userdto.UserDTO;

public interface UserMgmtService {
	
	public UserDTO registerUser(UserDTO userDTO);
	
	 public boolean isUsernameExists(String username);

	public User getUserByUsername(String username);

}
