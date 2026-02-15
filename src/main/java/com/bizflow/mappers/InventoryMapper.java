package com.bizflow.mappers;

import com.bizflow.models.Branch;
import com.bizflow.models.Inventory;
import com.bizflow.models.Product;
import com.bizflow.payloads.dto.InventoryDto;

public class InventoryMapper {
	
	public static InventoryDto toDto(Inventory inventory) {
		return InventoryDto.builder()
				.id(inventory.getId())
				.branch(inventory.getBranch()!=null ? BranchMapper.toDto(inventory.getBranch()) : null)
				.product(inventory.getProduct() !=null? ProductMapper.toDto(inventory.getProduct()):null)
				.quantity(inventory.getQuantity())
				.branchId(inventory.getBranch().getId())
				.productId(inventory.getProduct().getId())
				.build();
	}
	
	public static Inventory toEntity(InventoryDto inventoryDto,Product product,Branch branch) {
		return Inventory.builder()
				.branch(branch)
				.product(product)
				.quantity(inventoryDto.getQuantity())
				.build();
	}

}
