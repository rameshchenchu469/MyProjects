package com.nt.userdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	
	private Integer uid;
	private String fullName;
	private LocalDate dob;
	private String address;
	private String username;
	private String password;
	private String role;

}
