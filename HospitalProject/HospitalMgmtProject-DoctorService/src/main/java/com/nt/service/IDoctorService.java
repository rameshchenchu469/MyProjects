package com.nt.service;

import java.util.List;

import com.nt.dto.DoctorDTO;
import com.nt.entity.Doctor;

public interface IDoctorService {
	
	public DoctorDTO registerDoctor(DoctorDTO DoctorDTO);
	
	public Iterable<Doctor> getAllDoctors();

	public DoctorDTO getDoctorById(Long id);
	
	public List<Doctor> getDoctorsByHospitalId(Integer id);
	
	public String deleteByDoctorId(Long doctorId);

	public DoctorDTO updateDoctor(DoctorDTO doctorDTO);

}
