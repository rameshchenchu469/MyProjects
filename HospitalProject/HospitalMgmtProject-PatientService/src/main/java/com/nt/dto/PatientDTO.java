package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
	
	private Long patientId;
	private String name;
	private Integer age;
	private String gender;
	private String addrs;
	private Long contactNo;
	private String email;
	private String referencedBy;
	
}
