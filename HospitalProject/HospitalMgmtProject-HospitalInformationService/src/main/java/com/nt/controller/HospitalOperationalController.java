package com.nt.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.HospitalDTO;
import com.nt.entity.Doctor;
import com.nt.entity.Hospital;
import com.nt.exception.HospitalNotFoundException;
import com.nt.service.HospitalService;

@RestController
@RequestMapping("/hospital")
//@CrossOrigin(origins="http://localhost:3000")
public class HospitalOperationalController {

	@Autowired
	private HospitalService hospitalService;
	
	@PostMapping("/register-hospital")
	public String addHospital(@RequestBody HospitalDTO hospitalDTO) {
		
		String response = hospitalService.regsiterHospital(hospitalDTO);
		return response;
	}
	
	@GetMapping("/getAll")
	public List<Hospital> getAllHospitals(){
		List<Hospital> hospitalList = hospitalService.fetchAll();
		return hospitalList;
	}
	
	@GetMapping("/getHospitalById/{hospitalId}")
	public Hospital fetchHospitalById(@PathVariable Integer hospitalId) throws HospitalNotFoundException {
		return hospitalService.getHopsitalById(hospitalId);
	}
	
	@GetMapping("/doctors/{hid}")
    public List<Doctor> fetchDoctorsByHospitalId(@PathVariable Integer hid) {
        return hospitalService.getDoctorsByHospitalId(hid);
    }
}
