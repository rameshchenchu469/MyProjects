package com.nt.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	
	private Integer userId;
	private String username;
	private String email;
	private String password;
	private Long contactNo;
	private String role;
	private String relatedId;
}
