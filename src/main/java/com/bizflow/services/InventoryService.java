package com.bizflow.services;

import java.util.List;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.InventoryDto;

public interface InventoryService {
	InventoryDto createInventory(InventoryDto inventoryDto) throws UserException;
	InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws UserException;
	void deleteInventory(Long id) throws UserException;
	InventoryDto getInventoryById(Long id) throws UserException;
	InventoryDto getInventoryByProductAndBranchId(Long productId,Long BranchId) throws UserException;
	List<InventoryDto> getAllInventoryByBranchId(Long BranchId) throws UserException;
}
