package com.nt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nt.entity.Prescription;

public interface PrescriptonRepository extends MongoRepository<Prescription,Long>{

}
