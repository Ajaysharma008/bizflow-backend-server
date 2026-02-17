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

import com.bizflow.constants.UserRole;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1. Create Store Employee
    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(
            @RequestBody UserDto employee,
            @PathVariable Long storeId) throws Exception {

        UserDto createdEmployee = employeeService.createStoreEmployee(employee, storeId);
        return ResponseEntity.ok(createdEmployee);
    }


    // 2. Create Branch Employee
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(
            @RequestBody UserDto employee,
            @PathVariable Long branchId) throws Exception {

        UserDto createdEmployee = employeeService.createBranchEmployee(employee, branchId);
        return ResponseEntity.ok(createdEmployee);
    }


    // 3. Update Employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<User> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody UserDto employeeDetails) throws Exception {

        User updatedEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }


    // 4. Delete Employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long employeeId) throws Exception {

        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.ACCEPTED,"Employee deleted successfully",LocalDateTime.now()));
    }


    // 5. Get Branch Employees (optional role filter)
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<User>> getBranchEmployees(
            @PathVariable Long branchId,
            @RequestParam(required = false) UserRole role) throws Exception {

        List<User> employees = employeeService.findBranchEmployees(branchId, role);
        return ResponseEntity.ok(employees);
    }


    // 6. Get Store Employees (optional role filter)
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<User>> getStoreEmployees(
            @PathVariable Long storeId,
            @RequestParam(required = false) UserRole role) throws Exception {

        List<User> employees = employeeService.findStoreEmployees(storeId, role);
        return ResponseEntity.ok(employees);
    }

}
