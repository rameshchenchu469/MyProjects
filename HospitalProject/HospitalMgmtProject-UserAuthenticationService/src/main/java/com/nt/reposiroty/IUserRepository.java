package com.nt.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.nt.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	public UserDetails findByUsername(String username);

	public User findUserByUsername(String username);

}
