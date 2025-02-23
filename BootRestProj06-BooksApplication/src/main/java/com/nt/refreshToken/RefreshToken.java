package com.nt.refreshToken;

import java.time.Instant;

import com.nt.userEntity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Jwt_RefreshToken")
@Entity
@Builder
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String token;
	
	private Instant expiryDate;
	
	@OneToOne()
	@JoinColumn(name="user_id",referencedColumnName="id")
	private UserEntity userEntity;
	
}
