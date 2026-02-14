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
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.BranchDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.BranchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {
	
	private final BranchService branchService;
	
	
	@PostMapping
	public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto)throws UserException{
		System.out.println(branchDto);
		BranchDto createdBranch = branchService.createBranch(branchDto);
		return ResponseEntity.ok(createdBranch);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws UserException{
		BranchDto branch = branchService.getBranchById(id);
		
		return ResponseEntity.ok(branch);
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<List<BranchDto>> getAllBranchByStoreId(@PathVariable Long storeId) throws UserException{
		List<BranchDto> branches = branchService.getAllBranchesByStoreId(storeId);
		
		return ResponseEntity.ok(branches);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BranchDto> UpdateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) throws UserException{
		BranchDto branch = branchService.updateBranch(id,branchDto);
		return ResponseEntity.ok(branch);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws UserException{
		branchService.deleteBranch(id);
		
		return ResponseEntity.ok(new ApiResponse(HttpStatus.ACCEPTED,"Branch Deleted Sucessfully",LocalDateTime.now()));
	}
	

}
