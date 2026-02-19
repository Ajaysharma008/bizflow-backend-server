package com.bizflow.mappers;

import com.bizflow.models.OrderItem;
import com.bizflow.payloads.dto.OrderItemDto;

public class OrderItemMapper {
	
	public static OrderItemDto toDto(OrderItem orderItem) {
		
		if(orderItem==null) return null;
		
		return OrderItemDto.builder()
				.id(orderItem.getId())
				.price(orderItem.getPrice())
				.quantity(orderItem.getQuantity())
				.productId(orderItem.getProduct().getId())
				.productDto(ProductMapper.toDto(orderItem.getProduct()))
				.build();
	}

}
