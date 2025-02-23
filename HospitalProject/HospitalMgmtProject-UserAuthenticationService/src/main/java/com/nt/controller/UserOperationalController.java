package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.AuthRequest;
import com.nt.dto.AuthResponse;
import com.nt.dto.UserDTO;
import com.nt.entity.User;
import com.nt.jwtService.JwtService;
import com.nt.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserOperationalController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/signup-user")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
		
		try {
			userService.registerUser(userDTO);
			String response ="user registered successsfully";
			return new ResponseEntity<String>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Getting error while registering user",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/generate-token")
	public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
		
			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
			if(authentication.isAuthenticated()) {
			String token = jwtService.generateToken(authRequest.getUsername());
			User user = userService.getUserByUsername(authRequest.getUsername());
			
			AuthResponse response = new AuthResponse();
			
			response.setToken(token);
			response.setUser(user);
			
			return response;
			}
			else {
				throw new RuntimeException ("Invalid Login Details");
			}
		
	}
}
