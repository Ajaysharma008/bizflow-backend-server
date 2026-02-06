package com.bizflow.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.constants.StoreStatus;
import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.StoreMapper;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.StoreDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.StoreService;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreContoller {
	
	private final StoreService storeService;
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) throws UserException{
		
		User currentUser = userService.getCurrentUser();
		StoreDto createdStore = storeService.createStore(storeDto, currentUser);
		return ResponseEntity.ok(createdStore);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) throws UserException {
		return ResponseEntity.ok(storeService.getStoreById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<StoreDto>> getAllStore() throws UserException{
		return ResponseEntity.ok(storeService.getAllStores());
	}
	
	@GetMapping("/admin")
	public ResponseEntity<StoreDto> getStoreByAdmin() throws UserException{
		return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
	}
	
	@GetMapping("/employee")
	public ResponseEntity<StoreDto> getStoreByEmployee() throws UserException{
		return ResponseEntity.ok(storeService.getStoreByEmployee());
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,@RequestBody StoreDto storeDto) throws UserException{
		return ResponseEntity.ok(storeService.updateStore(id, storeDto));
	}
	
	@PatchMapping("/{id}/moderate")
	public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,@RequestParam StoreStatus storeStatus) throws UserException{
		return ResponseEntity.ok(storeService.moderateStore(id, storeStatus));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws UserException{
		storeService.deleteStore(id);
		
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"Store deleted sucessfully",LocalDateTime.now()
				));
	}
	
	
	
	
	
}
