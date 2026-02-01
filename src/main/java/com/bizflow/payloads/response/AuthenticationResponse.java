package com.bizflow.payloads.response;

import com.bizflow.payloads.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
	
	private String jwt;
	private String message;
	private UserDto userDto;

}
