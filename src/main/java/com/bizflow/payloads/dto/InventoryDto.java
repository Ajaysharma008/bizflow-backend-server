package com.bizflow.payloads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDto {
	
	private Long id;
	
	private BranchDto branch;
	
	private ProductDto product;
	private Long branchId;
	private Long productId;
	private Integer quantity;

}
