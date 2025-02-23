package com.nt.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.UserEntity.User;
import com.nt.config.JwtProvider;
import com.nt.exception.UserException;
import com.nt.request.LoginRequest;
import com.nt.response.AuthResponse;
import com.nt.userRepository.UserRepository;
import com.nt.userService.CustomUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserServiceImpl customUserService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler (@RequestBody User user)throws UserException{
		
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isEmailExist = userRepo.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("email already used with another account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepo.save(createdUser);
		
		Authentication anthentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(anthentication);
		
		String token = jwtProvider.generateToken(anthentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("SIgnUp Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED) ;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest)throws UserException{
	
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("SignIn Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED) ;
	}
		
		public Authentication   authenticate(String username,String password) {
			UserDetails userDetails = customUserService.loadUserByUsername(username);
		
			if(userDetails == null) {
				throw new BadCredentialsException("Invalid Username");
			}
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("Invalid Password....");
			}
			
			
			return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
	

}
