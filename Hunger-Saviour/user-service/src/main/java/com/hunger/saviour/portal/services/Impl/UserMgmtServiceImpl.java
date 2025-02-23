package com.hunger.saviour.portal.services.Impl;


import com.hunger.saviour.portal.dtos.RolesDTO;
import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.entities.RoleEntity;
import com.hunger.saviour.portal.entities.UserEntity;
import com.hunger.saviour.portal.repositories.RoleRepository;
import com.hunger.saviour.portal.repositories.UserRepository;
import com.hunger.saviour.portal.services.IUserMgmtService;
import com.hunger.saviour.portal.utilities.UserMapper;
import com.hunger.saviour.portal.utilities.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {
    @Autowired
    private UserRepository userRepo;

    private
    @Autowired RoleRepository rolesRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Override
    public SignUpRequest addUser(SignUpRequest  signUp) {

        List<RoleEntity> roleEntities = signUp.getRoles().stream()
                .map(roleDTO -> {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setRoles(roleDTO.getRoles());
                    return roleEntity;
                })
                .collect(Collectors.toList());

        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        UserEntity userEntity = UserEntity.builder()
                .username(signUp.getUsername())
                .password(signUp.getPassword())
                .roles(roleEntities)
                .created_at(signUp.getCreated_at())
                .build();
        userRepo.save(userEntity);

        List<RolesDTO> rolesDTOList=userEntity.getRoles().stream()
        .map(rEntity-> {
            RolesDTO role = new RolesDTO();
            role.setRoles(rEntity.getRoles());
            return role;
        }).collect(Collectors.toList());

        SignUpRequest  dto=SignUpRequest.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(rolesDTOList)
                .created_at(userEntity.getCreated_at())
                .build();
        return dto;

    }


}
