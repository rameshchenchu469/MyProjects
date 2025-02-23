package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nt.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	public List<Doctor> getDoctorsByHospitalId(Integer hId);
	public Optional<Doctor> deleteByDoctorId(Long doctorId);
	public Optional<Doctor> findByDoctorId(Long doctorId);
}
