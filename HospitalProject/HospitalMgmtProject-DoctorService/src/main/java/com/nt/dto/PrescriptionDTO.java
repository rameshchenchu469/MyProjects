package com.nt.dto;

import lombok.Data;

@Data
public class PrescriptionDTO {
	private Long id;
    private String medication;
    private String dosage;
    private Long doctorId; // reference to Doctor ID

}
