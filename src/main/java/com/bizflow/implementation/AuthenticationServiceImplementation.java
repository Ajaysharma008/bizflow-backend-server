package com.bizflow.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizflow.authorization.JwtProvider;
import com.bizflow.constants.UserRole;
import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.UserMapper;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.payloads.request.LoginRequest;
import com.bizflow.payloads.response.AuthenticationResponse;
import com.bizflow.repositories.UserRepository;
import com.bizflow.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;
	
	
	
	@Override
	public AuthenticationResponse register(UserDto userDto) throws UserException {
		
		User user = userRepository.findByEmail(userDto.getEmail());
		
		if(user !=null) throw new UserException("Email is already registered. Please use another email to register");
		
		if(userDto.getRole().equals(UserRole.ROLE_ADMIN)) throw new UserException("role admin is not allowed");
		
		User newUser = new User();
		newUser.setFullName(userDto.getFullName());
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		newUser.setPhone(userDto.getPhone());
		newUser.setRole(userDto.getRole());
		newUser.setLastLogin(LocalDateTime.now());
		
		User savedUser = userRepository.save(newUser);

		Authentication authentication = new 
				UsernamePasswordAuthenticationToken(
						userDto.getEmail(),
						userDto.getPassword(),
						List.of(new SimpleGrantedAuthority(savedUser.getRole().name()))
						);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		
		UserDto responseDto = UserMapper.toDTO(savedUser);
		
		return new AuthenticationResponse(jwt,"User registered sucessfully", responseDto);
		
		
	}
	
	@Override
	public AuthenticationResponse login(LoginRequest loginRequest) throws UserException {

	    
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getEmail(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = jwtProvider.generateToken(authentication);

	        User loginUser = userRepository.findByEmail(loginRequest.getEmail());
	        System.out.println(loginUser);
	        loginUser.setLastLogin(LocalDateTime.now());
	        User savedUser = userRepository.save(loginUser);
	        System.out.println(loginUser);
	        UserDto loggedInUser = UserMapper.toDTO(savedUser);

	        return new AuthenticationResponse(
	                jwt,
	                "Login successful",
	                loggedInUser
	        );

	    } 
}

