package com.hunger.saviour.portal.services;



import com.hunger.saviour.portal.dtos.RolesDTO;
import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.entities.RoleEntity;
import com.hunger.saviour.portal.entities.UserEntity;

import java.util.List;

public interface IUserMgmtService {
    public SignUpRequest addUser(SignUpRequest signUp);

}