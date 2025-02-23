package com.te.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.entity.SearchEntity;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {

}
