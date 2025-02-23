package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
