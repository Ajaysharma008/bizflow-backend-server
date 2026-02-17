package com.bizflow.payloads.dto;

import java.time.LocalDateTime;

import com.bizflow.constants.UserRole;

import lombok.Data;


@Data
public class UserDto {
	
	
	
	private String fullName;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private Long branchId;
	private Long storeId;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime lastLogin;
	
	
	private UserRole role;
	

}
