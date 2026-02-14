package com.bizflow.services;

import java.util.List;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.BranchDto;

public interface BranchService {
	
	BranchDto createBranch(BranchDto branchDTO) throws UserException; 
	BranchDto updateBranch(Long id, BranchDto branchDTO) throws UserException; 
	void deleteBranch(Long id) throws UserException; 
	List<BranchDto> getAllBranchesByStoreId(Long storeId) throws UserException;
	
	BranchDto getBranchById(Long id) throws UserException;
	
	

}
