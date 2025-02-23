package com.nt.userRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.userEntity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public  boolean existsByUsername(String username);

	public Optional<User> findByUsername(String username);

}
