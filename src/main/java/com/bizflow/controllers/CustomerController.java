package com.bizflow.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.payloads.dto.CustomerDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.CustomerServie;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServie customerService;

    // 1. Create Customer
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody CustomerDto customerDto) {

        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(createdCustomer);
    }

    // 2. Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) throws Exception {

        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    // 3. Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(
            @PathVariable Long id) throws Exception {

        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.ACCEPTED,"Customer deleted successfully",LocalDateTime.now()));
    }

    // 4. Get Single Customer
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(
            @PathVariable Long id) throws Exception {

        CustomerDto customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    // 5. Get All Customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() throws Exception {

        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // 6. Search Customers
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> searchCustomers(
            @RequestParam String keyword) throws Exception {

        List<CustomerDto> customers = customerService.searchCustomers(keyword);
        return ResponseEntity.ok(customers);
    }

}
