package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Integer>{
	
	public Optional<Hospital> findByHospitalId(Integer hid);

}
