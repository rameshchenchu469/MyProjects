package com.nt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.AccEntity;

public interface AccountRepository extends JpaRepository<AccEntity, Integer> {

	public Optional<AccEntity> findByAccNo(Long accNo);
	
	@Query("SELECT a.balance FROM AccEntity a WHERE a.accNo = :accNo")
    Double findBalanceByAccNo(@Param("accNo") Long accNo);

	public Optional<List<AccEntity>> findByUser_Uid(Integer uid);
}
