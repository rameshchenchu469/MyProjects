package com.hunger.saviour.portal.dtos;

import com.hunger.saviour.portal.entities.RoleEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    @NotBlank(message="username mandatory")
    private String username;
    @NotBlank(message="user password mandatory")
    private String password;
    @NotBlank(message="user must provide at least one role")
    private List<RolesDTO> roles ;
    @NotBlank(message="created time mandatory")
    private LocalDateTime created_at;

}
