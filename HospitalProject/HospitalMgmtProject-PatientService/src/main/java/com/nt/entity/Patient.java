package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="patient_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long patientId;
	private String name;
	private Integer age;
	private String gender;
	private String addrs;
	private Long contactNo;
	private String email;
	private String referencedBy;
}
