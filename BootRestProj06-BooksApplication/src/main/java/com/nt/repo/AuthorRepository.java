package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
