package com.nt.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
	
    private Long doctorId;
    private String name;
    private String specialization;
    private Integer hospitalId;
    private Integer exp;
}
