package com.hunger.saviour.portal.repositories;


import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByUsername(String username);

}
