package com.nt.dto;

import com.nt.userEntity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDTO {

	private Integer accId;
	private Long accNo; 
	private String accHolderName;
	private Double balance;
	private Long contactNo;
	private String email; 
	private Long aadharId;
	private String panId;
	private User user;
}
