package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.client.DoctorServiceFeignClient;
import com.nt.dto.HospitalDTO;
import com.nt.entity.Doctor;
import com.nt.entity.Hospital;
import com.nt.exception.HospitalNotFoundException;
import com.nt.repository.HospitalRepository;

@Service
public class HopsitalMgmtServiceImpl implements HospitalService{
	
	@Autowired
	private HospitalRepository hospitalRepo;
	
	@Autowired
    private DoctorServiceFeignClient doctorServiceFeignClient;

	@Override
	public String regsiterHospital(HospitalDTO hospitalDTO) {
		
		Hospital hospital = new Hospital();
		
		hospital.setHospitalName(hospitalDTO.getHospitalName());
		hospital.setLocation(hospitalDTO.getLocation());
		hospital.setContactNo(hospitalDTO.getContactNo());
		hospital.setHospitalImageUrl(hospitalDTO.getHospitalImageUrl());
		Hospital hos = hospitalRepo.save(hospital);
		return "hospital registered successfully with id value:"+hos.getHospitalId();
	}

	@Override
	public List<Hospital> fetchAll() {
		return hospitalRepo.findAll();
	}

	@Override
	public Hospital getHopsitalById(Integer hospitalId) throws HospitalNotFoundException {
		Optional<Hospital> opt = hospitalRepo.findByHospitalId(hospitalId);
		
		if(opt.isPresent()) {
			Hospital hospital = opt.get();
			return hospital;
		}
		throw new HospitalNotFoundException("Inavalid hospital id");
		
	}

	@Override
	public List<Doctor> getDoctorsByHospitalId(Integer hid) {
		 return doctorServiceFeignClient.getDoctorsByHid(hid);
	}

	
}
