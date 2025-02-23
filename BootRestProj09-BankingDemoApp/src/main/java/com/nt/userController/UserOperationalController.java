package com.nt.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.jwt.JwtService;
import com.nt.userEntity.User;
import com.nt.userService.UserMgmtService;
import com.nt.userdto.LoginRequestDTO;
import com.nt.userdto.ResponseDTO;
import com.nt.userdto.UserDTO;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserOperationalController {
	
	@Autowired
	private UserMgmtService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
	    boolean usernameExists = userService.isUsernameExists(userDTO.getUsername());
	    
	    if (usernameExists) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                             .body("Username already exists. Please choose another.");
	    }
	    
	    // Proceed to register the user if username does not exist
	    UserDTO response = userService.registerUser(userDTO);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    
	
	@PostMapping("/login")
	public ResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequest) {
		
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
		String token = jwtService.generateToken(loginRequest.getUsername());
		User user = userService.getUserByUsername(loginRequest.getUsername());
		
		ResponseDTO dto = new ResponseDTO();
		dto.setToken(token);
		dto.setUser(user);
		
		return dto;
		}
		else {
			throw new UsernameNotFoundException("Invalid Login Details");
		}
	}
	
	
}
