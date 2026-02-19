package com.bizflow.payloads.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
	
	private Long id;
	private Integer quantity;
	private Double price;
	private ProductDto productDto;
	private Long productId;
	private Long orderId;

}
