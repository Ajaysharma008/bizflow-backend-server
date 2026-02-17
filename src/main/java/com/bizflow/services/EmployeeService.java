package com.bizflow.services;

import java.util.List;

import com.bizflow.constants.UserRole;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.UserDto;

public interface EmployeeService {
	
	UserDto createStoreEmployee(UserDto employee,Long storeId) throws Exception;
	UserDto createBranchEmployee(UserDto employee,Long branchId) throws Exception;
	User updateEmployee(Long employeeId,UserDto employeeDetails) throws Exception;
	void deleteEmployee(Long employeeId) throws Exception;
	List<User> findBranchEmployees(Long branchId, UserRole role) throws Exception;
	List<User> findStoreEmployees(Long storeId, UserRole role) throws Exception;

}
