package com.nt.tokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nt.jwt.JwtService;
import com.nt.refreshToken.RefreshToken;
import com.nt.refreshTokenRepo.RefreshTokenRepository;
import com.nt.securityConfig.UserInfoUserDetailsService;
import com.nt.userRepo.IUserRepository;

@Service
public class TokenService {
//
//	@Autowired
//	private JwtService jwtService;
//	
	@Autowired
	private IUserRepository userRepo; 
	
	@Autowired
	private RefreshTokenRepository tokenRepo;
	
	
	 public RefreshToken createRefreshToken(String username) {
		 
		RefreshToken refresh = RefreshToken.builder()
		 			 .userEntity(userRepo.findUserByUsername(username))
		 			 .token(UUID.randomUUID().toString())
		 			 .expiryDate(Instant.now().plusMillis(600000))
		 			 .build();
        
		return tokenRepo.save(refresh);
		
	    }
	 
	 public Optional<RefreshToken> getByToken(String rereshToken){
		 return tokenRepo.findByToken(rereshToken);
	 }
	 
	 public RefreshToken verifyExpiration(RefreshToken refreshToken) {
		 if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
			 tokenRepo.delete(refreshToken);
			 throw new RuntimeException(refreshToken.getToken()+"Refresh token was expired,plesae make new signin");
		 }
		 return refreshToken;
	 }
	 
}
