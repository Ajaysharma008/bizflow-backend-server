package com.bizflow.mappers;

import com.bizflow.models.Branch;
import com.bizflow.models.Store;
import com.bizflow.payloads.dto.BranchDto;

public class BranchMapper {
	
	
	public static BranchDto toDto(Branch branch) {
		
		
		return BranchDto.builder()
				.id(branch.getId())
				.name (branch.getName())
				.address(branch.getAddress())
				.phone(branch.getPhone())
				.email(branch.getEmail())
				.closeTime(branch.getCloseTime())
				.openTime(branch.getOpenTime())
				.workingDays(branch.getWorkingDays())
				.storeId(branch.getStore() != null?branch.getStore().getId():null)
				.createdAt(branch.getCreatedAt())
				.updatedAt(branch.getUpdatedAt())
				.build();
	}
	
	public static Branch toEntity(BranchDto branchDto,Store store) {
		return Branch.builder()
				.name (branchDto. getName ())
				.address(branchDto.getAddress())
				.store(store)
				.email(branchDto.getEmail())
				.phone (branchDto.getPhone())
				.closeTime(branchDto.getCloseTime())
				.openTime(branchDto.getOpenTime())
				.workingDays(branchDto.getWorkingDays())
				.build();
	}

}
