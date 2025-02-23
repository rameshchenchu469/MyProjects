package com.nt.refreshTokenRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.refreshToken.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

public 	Optional<RefreshToken> findByToken(String token);

}
