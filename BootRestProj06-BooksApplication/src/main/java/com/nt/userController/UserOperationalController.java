package com.nt.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nt.dto.AuthRequest;
import com.nt.jwt.JwtService;
import com.nt.refreshToken.RefreshToken;
import com.nt.refreshToken.RefreshTokenRequest;
import com.nt.tokenService.TokenService;
import com.nt.userEntity.AuthResponse;
import com.nt.userEntity.AuthResponse.AuthResponseBuilder;
import com.nt.userEntity.UserEntity;
import com.nt.userService.IUserMgmtService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserOperationalController {
	
	@Autowired
	private IUserMgmtService userService;
	
	@Autowired
    private AuthenticationManager authManager;
	
		@Autowired
	    private JwtService jwtService;
		
		@Autowired
		private TokenService tokenService;
		
		
	
		
	@PostMapping("/register")
	public  ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity){
		String resultMsg = userService.registerUser(userEntity);
		return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest auth) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
	       if(authentication.isAuthenticated()) {
//	    	RefreshToken refreshToken = tokenService.createRefreshToken(auth.getUsername());
			String token = jwtService.generateToken(auth.getUsername());
			UserEntity user = userService.getUserByUsername(auth.getUsername());
			
						AuthResponse response = AuthResponse.builder()
												.token(token)
//												.refreshToken(refreshToken.getToken())
												.user(user)
												.build();
			
			return new ResponseEntity<AuthResponse>(response,HttpStatus.OK);
		}
	       else {
	    	   throw new UsernameNotFoundException("Invalid user request");
	       }
			
		}
	
	@GetMapping("/getUser/{name}")
	public ResponseEntity<UserEntity> fetchUserByUserName(@PathVariable String name) {
		
		 UserEntity user = userService.getUserByName(name);
		 System.out.println("userDetails"+user);
		 
		 
		 return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
	}
	
//	@PostMapping("/refreshToken")
//    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return  tokenService.getByToken(refreshTokenRequest.getRefreshToken())
//        			.map(tokenService::verifyExpiration)
//        			.map(RefreshToken::getUserEntity)
//        			.map(user->{
//        				String accessToken = jwtService.generateToken(user.getUsername());
//        				return AuthResponse.builder()
//        						.token(accessToken)
//        						.refreshToken(refreshTokenRequest.getRefreshToken())
//        						.build();
//        			}).orElseThrow(()->new RuntimeException("Refresh token is not in database"));
//	}
	
}
