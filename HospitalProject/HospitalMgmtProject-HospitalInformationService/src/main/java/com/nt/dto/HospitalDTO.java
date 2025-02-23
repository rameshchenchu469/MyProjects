package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HospitalDTO {

	private Integer hospitalId;
	private String hospitalName;
	private String location;
	private Long contactNo;
	private String hospitalImageUrl;
}
