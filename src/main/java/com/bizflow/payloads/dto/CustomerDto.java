package com.bizflow.payloads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
	
	
	private Long id;
	private String fullName;
	
	private String email;
	private String phone;

}
