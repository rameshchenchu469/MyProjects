package com.nt.userRepo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nt.userEntity.UserEntity;

public interface IUserRepository extends CrudRepository<UserEntity,Integer>{

public 	Optional<UserEntity> findByUsername(String username);
	
public UserEntity findUserByUsername(String username);

public UserEntity findUserByName(String name);

}
