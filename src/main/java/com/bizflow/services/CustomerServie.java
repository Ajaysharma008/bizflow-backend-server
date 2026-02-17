package com.bizflow.services;

import java.util.List;

import com.bizflow.payloads.dto.CustomerDto;

public interface CustomerServie {
	
	CustomerDto createCustomer(CustomerDto customerDto);
	CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws Exception;
	void deleteCustomer(Long id) throws Exception; 
	CustomerDto getCustomer(Long id) throws Exception; 
	List<CustomerDto> getAllCustomers() throws Exception;
	List<CustomerDto> searchCustomers(String keyword) throws Exception;

}
