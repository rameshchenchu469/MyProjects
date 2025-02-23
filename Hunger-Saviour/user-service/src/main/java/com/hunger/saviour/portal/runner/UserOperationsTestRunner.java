package com.hunger.saviour.portal.runner;

import com.hunger.saviour.portal.entities.RoleEntity;
import com.hunger.saviour.portal.entities.UserEntity;
import com.hunger.saviour.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class UserOperationsTestRunner implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        RoleEntity rEntity=new RoleEntity();
        rEntity.setRoles("USER");
//        RoleEntity rEntity1=new RoleEntity();
//        rEntity.setRoles("ADMIN");

        UserEntity entity=new UserEntity();
        entity.setUsername("kavya");
        entity.setPassword(passwordEncoder.encode("kv@143"));
        entity.setRoles(List.of(rEntity));
        entity.setCreated_at(LocalDateTime.now());
        userRepository.save(entity);
    }
}
