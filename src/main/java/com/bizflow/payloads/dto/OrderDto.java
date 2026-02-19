package com.bizflow.payloads.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bizflow.constants.PaymentType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
	
	private Long id;
	
	private Double totalAmount;
	
	private LocalDateTime createdAt;
	
	private Long branchId;
	
	private long customerId;
	
	
	
	private BranchDto branch;
	
	private UserDto cashier;
	
	private PaymentType paymentType;
	private CustomerDto customer;
	

	private List<OrderItemDto> items;

}
