package com.nt.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nt.securityFilter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig  {
	
	@Autowired
	private JwtAuthFilter authFilter;
	
	@Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }


	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf(csrf -> csrf.disable())
	                .cors().and() // Enable CORS
	                .authorizeHttpRequests(requests -> requests
	                        .requestMatchers("/user/signup", "/user/login", "/user/check-username/**").permitAll()
	                        .requestMatchers("/bank/**").authenticated())
	                .sessionManagement(management -> management
	                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authenticationProvider(authenticationProvider())
	                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.addAllowedOrigin("http://localhost:3000"); // Allow your frontend origin
	        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
	        configuration.addAllowedHeader("*"); // Allow all headers
	        configuration.setAllowCredentials(true); // Allow cookies if required
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration); // Apply CORS to all endpoints
	        return source;
	    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
		

}
