package com.bizflow.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.payloads.request.LoginRequest;
import com.bizflow.payloads.response.AuthenticationResponse;
import com.bizflow.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;
	

//http://localhost:9000/auth/signup	

	@PostMapping("/signup")
	public ResponseEntity<AuthenticationResponse> signupHandler( @RequestBody UserDto userDto) throws UserException{
		AuthenticationResponse registeredUserResponse = authenticationService.register(userDto);
		
		return ResponseEntity.ok(registeredUserResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> loginHandler( @RequestBody LoginRequest loginRequest) throws UserException{
		AuthenticationResponse logInUserResponse = authenticationService.login(loginRequest);
		
		return ResponseEntity.ok(logInUserResponse);
	}

}
