package com.nt.service;

import java.util.List;

import com.nt.dto.PatientDTO;
import com.nt.entity.Patient;

public interface IPatientService {
	
	public PatientDTO registerPatient(PatientDTO patientDTO);
	
	public List<Patient> getAllPatients();
	
	public String deletePatient(Long patientId);

	public PatientDTO updatePatient(PatientDTO patientDTO);

}
