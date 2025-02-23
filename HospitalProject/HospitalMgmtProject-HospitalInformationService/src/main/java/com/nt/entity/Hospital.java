package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="hospital_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hospital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hospitalId;
	private String hospitalName;
	private String location;
	private Long contactNo;
	private String hospitalImageUrl;
}
