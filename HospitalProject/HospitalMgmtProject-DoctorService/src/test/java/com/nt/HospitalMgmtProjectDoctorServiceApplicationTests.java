package com.nt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nt.entity.Doctor;
import com.nt.repository.DoctorRepository;
import com.nt.service.IDoctorService;

@SpringBootTest
class HospitalMgmtProjectDoctorServiceApplicationTests {
	
	@Autowired
	private IDoctorService doctorService;
	
	@MockBean
	private DoctorRepository doctorRepo;

	@Test
	public void getDoctors() {
		List<Doctor> doctors = List.of(new Doctor(12122L,"surya","orthopedic",1,10),
				new Doctor(32324L,"raghava","peditrcian",2,12),
				new Doctor(53552L,"hariharanath","generalMedicine",3,20));
		
		when(doctorRepo.findAll()).thenReturn(doctors);
		
		Iterable<Doctor> result = doctorService.getAllDoctors();
		assertEquals(3,((List<Doctor>) result).size());
	}

}
