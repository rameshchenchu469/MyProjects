package com.nt.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

	private Long pId;
	private LocalDateTime preDate;
	private List<String> medicines;
	private String dosage;
	private Long PatientId;
	private String doctorName;
	private Long doctorId;
}
