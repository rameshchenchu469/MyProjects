package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.dto.HospitalDTO;
import com.nt.entity.Doctor;
import com.nt.entity.Hospital;
import com.nt.exception.HospitalNotFoundException;

public interface HospitalService {

	public String regsiterHospital(HospitalDTO hospitalDTO);

	public List<Hospital> fetchAll();
	
	public Hospital getHopsitalById(Integer hospitalId)throws HospitalNotFoundException;

	public List<Doctor> getDoctorsByHospitalId(Integer hid);
}
