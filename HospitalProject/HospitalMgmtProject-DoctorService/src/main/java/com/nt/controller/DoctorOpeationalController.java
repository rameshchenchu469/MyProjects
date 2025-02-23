package com.nt.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.nt.dto.DoctorDTO;
import com.nt.entity.Doctor;
import com.nt.service.IDoctorService;

@RestController
@RequestMapping("/api/doctor")
//@CrossOrigin(origins="http://localhost/3000")
public class DoctorOpeationalController {

	@Autowired
	private IDoctorService doctorService;
	
	@PostMapping("/save-doctor")
	public ResponseEntity<?> addorUpdateDoctor(@RequestBody DoctorDTO doctorDTO) {
		
		try {
			if(doctorDTO.getDoctorId() != null) {
				DoctorDTO response = doctorService.updateDoctor(doctorDTO);
				return new ResponseEntity<DoctorDTO>(response,HttpStatus.OK);
			}
			else {
		DoctorDTO response = doctorService.registerDoctor(doctorDTO);
		return new ResponseEntity<DoctorDTO>(response,HttpStatus.CREATED);
			}}
		catch(Exception e) {
			return new ResponseEntity<String>("Error while regitser doctor",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-doctors")
	public ResponseEntity<?> fetchAllDoctors(){
		try {
		List<Doctor> response = (List<Doctor>) doctorService.getAllDoctors();
		return new ResponseEntity<List<Doctor>>(response,HttpStatus.OK); 
	}
		catch(Exception e) {
			return new ResponseEntity<String>("getting error while fetchinh doctors information",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getDoctorById/{id}")
	public ResponseEntity<?> fetchDoctorById(@PathVariable Long id){
		try {
			DoctorDTO doctor = doctorService.getDoctorById(id);
			return new ResponseEntity<DoctorDTO>(doctor,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error while getting doctor by using id",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getDoctorsByHid/{hid}")
	public List<Doctor> fetchAllDoctorsByHospitalId(@PathVariable Integer hid){
		List<Doctor> doctors = doctorService.getDoctorsByHospitalId(hid);
		return doctors;
	}
	
	@DeleteMapping("/delete")
	public String removeDoctor(Long doctorId) {
		String response = doctorService.deleteByDoctorId(doctorId);
		return response;
	}
}
