package com.nt.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.UserEntity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	public User findByEmail(String email);
}
