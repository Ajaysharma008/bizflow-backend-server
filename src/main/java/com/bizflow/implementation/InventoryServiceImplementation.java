package com.bizflow.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.InventoryMapper;
import com.bizflow.models.Branch;
import com.bizflow.models.Inventory;
import com.bizflow.models.Product;
import com.bizflow.payloads.dto.InventoryDto;
import com.bizflow.repositories.BranchRepository;
import com.bizflow.repositories.InventoryRepository;
import com.bizflow.repositories.ProductRepository;
import com.bizflow.services.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryService {
	
	private final InventoryRepository inventoryRepository;
	private final BranchRepository branchRepository;
	private final ProductRepository productRepository;
	
	
	@Override
	public InventoryDto createInventory(InventoryDto inventoryDto) throws UserException {
		
		Branch branch = branchRepository.findById(inventoryDto.getBranchId())
				.orElseThrow(
					()-> new UserException("Branch not exists!!"));
		Product product = productRepository.findById(inventoryDto.getProductId())
				.orElseThrow(
				    ()-> new UserException("Product not found!!"));
		
		Inventory inventory = InventoryMapper.toEntity(inventoryDto, product, branch);
		Inventory savedInventory= inventoryRepository.save(inventory);
		
		return InventoryMapper.toDto(savedInventory);
	}

	
	
	
	@Override
	public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws UserException {
		Inventory inventory = inventoryRepository.findById(id)
		.orElseThrow(
				()->new UserException("Inventory not found"));
		
		inventory.setQuantity(inventoryDto.getQuantity());
		Inventory updatedInventory = inventoryRepository.save(inventory);
		return InventoryMapper.toDto(updatedInventory);
	}

	@Override
	public void deleteInventory(Long id) throws UserException {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(
						()->new UserException("Inventory not found"));
		inventoryRepository.delete(inventory);
		
	}

	@Override
	public InventoryDto getInventoryById(Long id) throws UserException {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(
						()->new UserException("Inventory not found"));
		return InventoryMapper.toDto(inventory);
	}

	@Override
	public InventoryDto getInventoryByProductAndBranchId(Long productId, Long BranchId) throws UserException {

		Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, BranchId)
				.orElseThrow(
						()-> new UserException("Inventroy not found !!"));
		
		
		return InventoryMapper.toDto(inventory);
	}

	@Override
	public List<InventoryDto> getAllInventoryByBranchId(Long BranchId) throws UserException {
		List<Inventory> inventories = inventoryRepository.findByBranchId(BranchId);
		if(inventories.isEmpty()) 
			throw new UserException("Inventory not found");
		
		return inventories.stream().map(inventory->InventoryMapper.toDto(inventory)).collect(Collectors.toList());
	}
	

}
