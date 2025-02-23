package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.DoctorDTO;
import com.nt.entity.Doctor;
import com.nt.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository doctorRepo;

	@Override
	public DoctorDTO registerDoctor(DoctorDTO doctorDTO) {
		
		Doctor doctor = new Doctor();
		
		doctor.setHospitalId(doctorDTO.getHospitalId());
		doctor.setName(doctorDTO.getName());
		doctor.setSpecialization(doctorDTO.getSpecialization());
		doctor.setExp(doctorDTO.getExp());
		
		Doctor doctor1 = doctorRepo.save(doctor);
		
		DoctorDTO savedDoctor = convertEntityToDTO(doctor1);
		return savedDoctor;
	}
	
	public DoctorDTO convertEntityToDTO(Doctor doctor) {
		DoctorDTO dto = new DoctorDTO();
		dto.setHospitalId(doctor.getHospitalId());
		dto.setSpecialization(doctor.getSpecialization());
		dto.setDoctorId(doctor.getDoctorId());
		dto.setName(doctor.getName());
		dto.setExp(doctor.getExp());
		return dto;
	}

	@Override
	public Iterable<Doctor> getAllDoctors() {
		return doctorRepo.findAll();
	}

	@Override
	public DoctorDTO getDoctorById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctorsByHospitalId(Integer id) {
		List<Doctor> doctorList = doctorRepo.getDoctorsByHospitalId(id);
		return doctorList;
	}

	@Override
	public String deleteByDoctorId(Long doctorId) {
		Optional<Doctor> opt = doctorRepo.deleteByDoctorId(doctorId);
		if(opt.isEmpty()) {
			
			return "Doctor Id Not Found";
		}
		Long id = opt.get().getDoctorId();
		return "Doctor having id value"+ id+"delted successfully";
	}

	@Override
	public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
		Optional<Doctor> opt = doctorRepo.findById(doctorDTO.getDoctorId());
		
		if(opt.isEmpty()) {
			throw new RuntimeException("Doctor ID Not FoundFor Upadtion");
		}

		Doctor doctor = opt.get();;
		
		doctor.setHospitalId(doctorDTO.getHospitalId());
		doctor.setName(doctorDTO.getName());
		doctor.setSpecialization(doctorDTO.getSpecialization());
		doctor.setExp(doctorDTO.getExp());
		
		Doctor doctor1 = doctorRepo.save(doctor);
		
		DoctorDTO updatedDoctor = convertEntityToDTO(doctor1);
		
		return updatedDoctor;
	}

}
