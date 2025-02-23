package com.nt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	 	private Long doctorId;
	    private String name;
	    private String specialization;
	    private Integer hospitalId;
	    private Integer exp;

}
