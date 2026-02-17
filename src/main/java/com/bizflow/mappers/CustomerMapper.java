package com.bizflow.mappers;

import com.bizflow.models.Customer;
import com.bizflow.payloads.dto.CustomerDto;

public class CustomerMapper {
	public static CustomerDto toDto(Customer customer) {
		return CustomerDto.builder()
				.id(customer.getId())
				.email(customer.getEmail())
				.fullName(customer.getFullName())
				.phone(customer.getPhone())
				.build();
	}
	
	public static Customer toEntity(CustomerDto customerdto) {
		return Customer.builder()
				.email(customerdto.getEmail())
				.fullName(customerdto.getFullName())
				.phone(customerdto.getPhone())
				.build();
	}
}
