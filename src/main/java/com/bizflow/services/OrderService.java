package com.bizflow.services;

import java.util.List;

import com.bizflow.constants.OrderStatus;
import com.bizflow.constants.PaymentType;
import com.bizflow.payloads.dto.OrderDto;

public interface OrderService {
	
	OrderDto createOrder(OrderDto orderDTO) throws Exception; 
	OrderDto getOrderById(Long id) throws Exception; 
	List<OrderDto>getOrdersByBranch(Long branchId, Long customerId,Long cashierId,PaymentType paymentType,OrderStatus status) throws Exception;
	List<OrderDto> getOrderByCashier(Long cashierId); 
	void deleteOrder(Long id) throws Exception;
	List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;
	List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception; 
	List<OrderDto> getTop5RecentOrdersByBranchById(Long branchId) throws Exception;
}
