package com.nt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nt.userEntity.UserEntity;
import com.nt.userRepo.IUserRepository;
import com.nt.userService.UserMgmtServiceImpl;

public class UserMgmtServiceImplTest {

	@Mock
    private IUserRepository userRepo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserMgmtServiceImpl userMgmtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("hello123");

        UserEntity savedUserEntity = new UserEntity();
        savedUserEntity.setId(2);
        savedUserEntity.setPassword("r77rhurh");

        when(encoder.encode("hello123")).thenReturn("r77rhurh");
        when(userRepo.save(userEntity)).thenReturn(savedUserEntity);

        // Act
        String result = userMgmtService.registerUser(userEntity);

        // Assert
        assertEquals("user registered successfully with id value 2", result);
        assertEquals("r77rhurh", userEntity.getPassword());
        verify(encoder, times(1)).encode("hello123");
        verify(userRepo, times(1)).save(userEntity);
    }
    
    @Test
    void getUserById() {
    	 
    	
    }
}
