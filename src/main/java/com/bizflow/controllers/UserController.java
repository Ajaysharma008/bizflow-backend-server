package com.bizflow.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.UserMapper;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")

@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile() throws UserException{
		UserDto userDto = UserMapper.toDTO(userService.getCurrentUser());
		return ResponseEntity.ok().body(userDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserProfileById(@PathVariable Long id) throws UserException{;
		UserDto userDto = UserMapper.toDTO(userService.getUserByid(id));
		return ResponseEntity.ok().body(userDto);
	}

}
