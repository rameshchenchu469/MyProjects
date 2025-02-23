package com.te.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.entity.WeatherEntity;

public interface WeatherRepository extends JpaRepository<WeatherEntity,Long>{

	
}
