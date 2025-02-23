package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	@Modifying
	@Query("delete from Patient p where p.id = :patientId")
	public int deleteByPatientId(@Param("patientId") Long patientId);

}
