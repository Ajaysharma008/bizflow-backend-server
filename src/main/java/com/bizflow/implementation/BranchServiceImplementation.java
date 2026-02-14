package com.bizflow.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.BranchMapper;
import com.bizflow.models.Branch;
import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.BranchDto;
import com.bizflow.repositories.BranchRepository;
import com.bizflow.repositories.StoreRepository;
import com.bizflow.services.BranchService;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImplementation implements BranchService {
	
	
	private final BranchRepository branchRepository;
	private final StoreRepository storeRepository;
	private final UserService userService;
	
	@Override
	public BranchDto createBranch(BranchDto branchDTO) throws UserException {
		
		User currentUser = userService.getCurrentUser();
		Store store = storeRepository.findByStoreAdminId(currentUser.getId());
		
		Branch branch = BranchMapper.toEntity(branchDTO, store);
		Branch createdBranch = branchRepository.save(branch);
		
		
		return BranchMapper.toDto(createdBranch);
	}

	@Override
	public BranchDto updateBranch(Long id, BranchDto branchDTO) throws UserException {
		
		Branch existingBranch = branchRepository.findById(id).orElseThrow(
				()-> new UserException("branch not exist"));
		existingBranch.setName(branchDTO.getName());
		existingBranch.setWorkingDays(branchDTO.getWorkingDays());
		existingBranch.setEmail(branchDTO.getEmail());
		existingBranch.setPhone(branchDTO.getPhone());
		existingBranch.setAddress(branchDTO.getAddress());
		existingBranch.setOpenTime(branchDTO.getOpenTime());
		existingBranch.setCloseTime(branchDTO.getCloseTime());
		
		Branch updatedBranch = branchRepository.save(existingBranch);
		return BranchMapper.toDto(updatedBranch);
	}

	@Override
	public void deleteBranch(Long id) throws UserException {
		Branch existingBranch = branchRepository.findById(id).orElseThrow(
				()-> new UserException("branch not exist"));
		
		branchRepository.delete(existingBranch);
		
	}

	@Override
	public List<BranchDto> getAllBranchesByStoreId(Long storeId) throws UserException {
		List<Branch> branches = branchRepository.findByStoreId(storeId);
		return branches.stream().map(branch -> BranchMapper.toDto(branch)).collect(Collectors.toList());
		
	}

	@Override
	public BranchDto getBranchById(Long id) throws UserException {
		Branch existingBranch = branchRepository.findById(id).orElseThrow(
				()-> new UserException("branch not exist"));
		return BranchMapper.toDto(existingBranch);
	}
	
	

}
