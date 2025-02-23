package com.nt.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresriptionDTO {
	
	private Long pId;
	private LocalDateTime preDate;
	private List<String> medicines;
	private String dosage;
	private Long PatientId;
	private String doctorName;
	private Long doctorId;

}
