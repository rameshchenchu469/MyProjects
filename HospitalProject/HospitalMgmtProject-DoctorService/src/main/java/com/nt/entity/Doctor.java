package com.nt.entity;

import java.util.Set;

import com.nt.dto.AppointmentDTO;
import com.nt.dto.PrescriptionDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Doctors_Table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long doctorId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "specialization", nullable = false)
	private String specialization;
	@Column(name = "hospital_id", nullable = false)
	private Integer hospitalId;
	@Column(name="experience",nullable = false)
	private Integer exp;

}
