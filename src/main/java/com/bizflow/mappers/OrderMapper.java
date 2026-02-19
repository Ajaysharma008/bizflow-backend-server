package com.bizflow.mappers;

import java.util.stream.Collectors;

import com.bizflow.models.Order;
import com.bizflow.payloads.dto.OrderDto;

public class OrderMapper {
	
	public static OrderDto toDto(Order order) {
		return OrderDto.builder()
				.id(order.getId())
				.branch(BranchMapper.toDto(order.getBranch()))
				.cashier(UserMapper.toDTO(order.getCashier()))
				.customer(CustomerMapper.toDto(order.getCustomer()))
				.paymentType(order.getPaymentType())
				.items(order.getItems().stream().map(OrderItemMapper::toDto).collect(Collectors.toList()))
				.build();
	}
	
	public static Order toEntity() {
		return null;
	}

}
