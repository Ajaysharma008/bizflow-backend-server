package com.bizflow.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.mappers.CustomerMapper;
import com.bizflow.models.Customer;
import com.bizflow.payloads.dto.CustomerDto;
import com.bizflow.repositories.CustomerRepository;
import com.bizflow.services.CustomerServie;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerServie {
	private final CustomerRepository customerRepository;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		
		return CustomerMapper
				.toDto(customerRepository
						.save(CustomerMapper
								.toEntity(customerDto)));
	}

	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws Exception {
		Customer existingCustomer = customerRepository.findById(id)
		.orElseThrow(
				()->new Exception("Customer not found with given id"));
		
		existingCustomer.setEmail(customerDto.getEmail());
		existingCustomer.setPhone(customerDto.getPhone());
		existingCustomer.setFullName(customerDto.getFullName());
		return CustomerMapper.toDto(customerRepository.save(existingCustomer));
	}

	@Override
	public void deleteCustomer(Long id) throws Exception {
		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(
						()->new Exception("Customer not found with given id"));
		customerRepository.delete(existingCustomer);
		
	}

	@Override
	public CustomerDto getCustomer(Long id) throws Exception {
		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(
						()->new Exception("Customer not found with given id"));
		return CustomerMapper.toDto(existingCustomer);
	}

	@Override
	public List<CustomerDto> getAllCustomers() throws Exception {
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) new Exception("No User register yet");
		return customers.stream().map(customer->CustomerMapper.toDto(customer)).collect(Collectors.toList());
	}

	@Override
	public List<CustomerDto> searchCustomers(String keyword) throws Exception {
		List<Customer> customers = customerRepository.findByFullNameContainingIgnoreCaseOrEmailIgnoreCase(keyword, keyword);
		if(customers.isEmpty()) new Exception("No Customer matching");
		return customers.stream().map(customer->CustomerMapper.toDto(customer)).collect(Collectors.toList());
	}
	
	
	
}
