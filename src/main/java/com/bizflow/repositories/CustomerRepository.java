package com.bizflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	List<Customer> findByFullNameContainingIgnoreCaseOrEmailIgnoreCase
	(String fullName,String email);
}
