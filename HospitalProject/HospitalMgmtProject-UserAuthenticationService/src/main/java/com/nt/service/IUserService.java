package com.nt.service;

import com.nt.dto.UserDTO;
import com.nt.entity.User;

public interface IUserService {
	
	public String registerUser(UserDTO userDTO);

	public User getUserByUsername(String username);

}
