package com.bizflow.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizflow.constants.UserRole;
import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.UserMapper;
import com.bizflow.models.Branch;
import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.UserDto;
import com.bizflow.repositories.BranchRepository;
import com.bizflow.repositories.StoreRepository;
import com.bizflow.repositories.UserRepository;
import com.bizflow.services.EmployeeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
	private final BranchRepository branchRepository;
	private final StoreRepository storeRepository;

	
	@Override
	public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
		Store store = storeRepository.findById(storeId)
				.orElseThrow(
						()->new Exception("Store not found with given storeid"));
		
		Branch branch = null;
		
		if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER) {
			if(employee.getBranchId()==null) {
				throw new Exception("branch id is required to create branch manager");
			}
			branch = branchRepository.findById(employee.getBranchId())
					.orElseThrow(()-> new Exception("branch not found"));
		}
		User user = UserMapper.toEntity(employee);
		user.setStore(store);
		user.setBranch(branch);
		user.setPassword(passwordEncoder.encode(employee.getPassword()));
		
		
		User savedEmployee = userRepository.save(user);
		if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch !=null) {
			branch.setManager(savedEmployee);
			branchRepository.save(branch);
		}
		
		return UserMapper.toDTO(savedEmployee);
	}

	@Override
	public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
		Branch branch = branchRepository.findById(branchId)
		.orElseThrow(
				()-> new Exception("branch not found"));
		
		if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER || employee.getRole()==UserRole.ROLE_BRANCH_MANAGER) {
			User user = UserMapper.toEntity(employee);
			user.setBranch(branch);
			user.setPassword(passwordEncoder.encode(employee.getPassword()));
			return UserMapper.toDTO(userRepository.save(user));
		}
		throw new UserException("branch role not supported");
	}

	@Override
	public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
		User existingEmployee = userRepository.findById(employeeId)
				.orElseThrow(
						()->new UserException("employee not exists with given id"));
		
		Branch branch = branchRepository.findById(employeeDetails.getBranchId())
				.orElseThrow(()-> new Exception("branch not found"));
		
		existingEmployee.setEmail(employeeDetails.getEmail());
		existingEmployee.setFullName(employeeDetails.getFullName());
		existingEmployee.setPassword(employeeDetails.getPassword());;
		existingEmployee.setRole(employeeDetails.getRole());
		existingEmployee.setBranch(branch);
		return userRepository.save(existingEmployee);
		
	}

	@Override
	public void deleteEmployee(Long employeeId) throws Exception {
		User existingEmployee = userRepository.findById(employeeId)
				.orElseThrow(
						()->new UserException("employee not exists with given id"));
		userRepository.delete(existingEmployee);
		
	}

	@Override
	public List<User> findBranchEmployees(Long branchId, UserRole role) throws Exception {
		branchRepository.findById(branchId)
				.orElseThrow(
						()-> new Exception("branch not found"));
		return userRepository.findByBranchId(branchId)
				.stream()
					.filter(user->role == null ||user.getRole()==role )
						.collect(Collectors.toList());
		
		 
	}

	@Override
	public List<User> findStoreEmployees(Long storeId, UserRole role) throws UserException {
		
		Store store = storeRepository.findById(storeId)
				.orElseThrow(
						()-> new UserException("Store not found"));
		
		return userRepository.findByStore(store)
				.stream()
					.filter(user->role == null ||user.getRole()==role )
						.collect(Collectors.toList());
	}

}
