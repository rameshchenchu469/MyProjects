package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.PatientDTO;
import com.nt.entity.Patient;
import com.nt.service.IPatientService;

@RequestMapping("/api/patient")
@RestController
//@CrossOrigin(origins="http://localhost:3000")
public class PatientOperationalController {
	
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/save-patient")
	public PatientDTO saveOrUpdatePatient(@RequestBody PatientDTO patientDTO) {
		if(patientDTO.getPatientId()!= null) {
			PatientDTO response = patientService.updatePatient(patientDTO);
			return response;
		}
		else {
			PatientDTO response = patientService.registerPatient(patientDTO);
			return response;
		}
		
	}
	
	@GetMapping("/fetchAll")
	public List<Patient> fetchAllPatients(){
		return patientService.getAllPatients();
	}
	
	@DeleteMapping("/delete/{patientId}")
	public String removePatient(@PathVariable Long patientId) {
		return patientService.deletePatient(patientId);
	}

}
