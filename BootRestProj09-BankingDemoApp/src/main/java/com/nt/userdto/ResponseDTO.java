package com.nt.userdto;

import com.nt.userEntity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
	
	private User user;
	private String token;

}
