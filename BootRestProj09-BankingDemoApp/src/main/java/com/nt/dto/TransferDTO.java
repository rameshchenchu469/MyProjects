package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
	
	private Long fromAccount;
	private Long toAccount;
	private Double amount;

}
