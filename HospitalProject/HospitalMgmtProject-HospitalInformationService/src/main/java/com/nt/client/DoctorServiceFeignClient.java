package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.entity.Doctor;

@FeignClient("doctor-service")
public interface DoctorServiceFeignClient {
	
	@GetMapping("api/doctor/getDoctorsByHid/{hid}")
	public List<Doctor> getDoctorsByHid(@PathVariable Integer hid);

}
