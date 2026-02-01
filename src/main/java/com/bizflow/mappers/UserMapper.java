package com.bizflow.mappers;

import com.bizflow.models.User;
import com.bizflow.payloads.dto.UserDto;

public class UserMapper {

	public static UserDto toDTO(User savedUser) {
		
		UserDto userDto = new UserDto();
		
		userDto.setFullName(savedUser.getFullName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setRole(savedUser.getRole());
		userDto.setCreatedAt(savedUser.getCreatedAt());
		userDto.setUpdatedAt(savedUser.getUpdatedAt());
		userDto.setLastLogin(savedUser.getLastLogin());
		userDto. setPhone(savedUser. getPhone());
		
		return userDto;
	}

}
