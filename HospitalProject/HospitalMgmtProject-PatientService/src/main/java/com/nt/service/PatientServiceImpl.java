package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.PatientDTO;
import com.nt.entity.Patient;
import com.nt.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository patientRepo;
	
	@Override
	public PatientDTO registerPatient(PatientDTO patientDTO) {
		
		Patient patient = new Patient();
		
		patient.setName(patientDTO.getName());
		patient.setAge(patientDTO.getAge());
		patient.setAddrs(patientDTO.getAddrs());
		patient.setContactNo(patientDTO.getContactNo());
		patient.setEmail(patientDTO.getEmail());
		patient.setGender(patientDTO.getGender());
		patient.setReferencedBy(patientDTO.getReferencedBy());
	
		patientRepo.save(patient);
		
		PatientDTO dto = convertEntityToDTO(patient);
		
		return dto;
	}
	
	public PatientDTO convertEntityToDTO(Patient patient) {
		
		PatientDTO dto = new PatientDTO();
		
		dto.setPatientId(patient.getPatientId());
		dto.setName(patient.getName());
		dto.setAddrs(patient.getAddrs());
		dto.setAge(patient.getAge());
		dto.setContactNo(patient.getContactNo());
		dto.setEmail(patient.getEmail());
		dto.setGender(patient.getGender());
		dto.setReferencedBy(patient.getReferencedBy());
		return dto;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
	}

	@Override
	public String deletePatient(Long patientId) {
	    int rowsDeleted = patientRepo.deleteByPatientId(patientId);

	    if (rowsDeleted == 0) {
	        return "Patient ID not found";
	    }

	    return "Patient deleted successfully";
	}

	@Override
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		
		Optional<Patient> opt = patientRepo.findById(patientDTO.getPatientId());
		
		if(opt.isPresent()) {
			Patient patient = opt.get();

			patient.setName(patientDTO.getName());
			patient.setAge(patientDTO.getAge());
			patient.setAddrs(patientDTO.getAddrs());
			patient.setContactNo(patientDTO.getContactNo());
			patient.setEmail(patientDTO.getEmail());
			patient.setGender(patientDTO.getGender());
			patient.setReferencedBy(patientDTO.getReferencedBy());
		
			Patient patient1 = patientRepo.save(patient);
			
			PatientDTO updatePatient = convertEntityToDTO(patient1);
			
			return updatePatient;
		}
		throw new IllegalArgumentException("Patient Id Not Found For updation");
	}

}
