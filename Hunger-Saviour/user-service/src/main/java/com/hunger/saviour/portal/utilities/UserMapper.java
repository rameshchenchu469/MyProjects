package com.hunger.saviour.portal.utilities;

import com.hunger.saviour.portal.dtos.RolesDTO;
import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.entities.RoleEntity;
import com.hunger.saviour.portal.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper   INSTANCE= Mappers.getMapper(UserMapper.class);

  RoleEntity dtoToEntity(RolesDTO dto);

  RolesDTO  EntityToDto(RoleEntity entity);
  UserEntity dtoToEntity(SignUpRequest signup);

  SignUpRequest entityToDto(UserEntity entity);

}
