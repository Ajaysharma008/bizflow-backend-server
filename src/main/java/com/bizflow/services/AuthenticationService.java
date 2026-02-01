package com.bizflow.services;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.payloads.request.LoginRequest;
import com.bizflow.payloads.response.AuthenticationResponse;


public interface AuthenticationService {
	AuthenticationResponse register(UserDto userDto) throws UserException;
	AuthenticationResponse login(LoginRequest loginRequest) throws UserException;
}
