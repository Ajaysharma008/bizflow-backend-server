package com.bizflow.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.constants.OrderStatus;
import com.bizflow.constants.PaymentType;
import com.bizflow.mappers.OrderMapper;
import com.bizflow.models.Branch;
import com.bizflow.models.Order;
import com.bizflow.models.OrderItem;
import com.bizflow.models.Product;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.OrderDto;
import com.bizflow.repositories.OrderRepository;
import com.bizflow.repositories.ProductRepository;
import com.bizflow.services.OrderService;
import com.bizflow.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final UserService userService;
	
	@Override
	public OrderDto createOrder(OrderDto orderDto) throws Exception {
		User cashier = userService.getCurrentUser();
		Branch branch = cashier.getBranch();
		if(branch==null) {
			 throw new Exception("cashier's branch is not exists");
		}
		
		Order order = Order.builder()
				.totalAmount(orderDto.getTotalAmount())
				.branch(branch)
				.cashier(cashier)
				.paymentType(orderDto.getPaymentType())
				.build();
		List<OrderItem> items = orderDto.getItems().stream()
				.map(item ->{
					Product product = productRepository.findById(item.getProductId()).orElseThrow(()-> new EntityNotFoundException("Product not found"));
			    	return OrderItem.builder()
			    			.quantity(item.getQuantity())
			    			.product(product!=null?product:null)
			    			.price(product.getSellingPrice()*item.getQuantity())
			    			.order(order)
			    			.build();
							}
					).collect(Collectors.toList());
		
		double total = items.stream().mapToDouble(OrderItem::getPrice).sum();
		order.setTotalAmount(total);
		order.setItems(items);
		return OrderMapper.toDto(orderRepository.save(order));
	}

	@Override
	public OrderDto getOrderById(Long id) throws Exception {
		
		return orderRepository.findById(id)
				.map(OrderMapper::toDto)
				.orElseThrow(()->new Exception("Order not found with id "+ id));
	}

	@Override
	public List<OrderDto> getOrdersByBranch(Long branchId,
			Long customerId,
			Long cashierId,
			PaymentType paymentType,
			OrderStatus status) throws Exception {
		
		return orderRepository.findByBranchId(branchId).stream()
				.filter(order->cashierId==null || 
						(order.getCashier() !=null &&
							order.getCashier().getId().equals(cashierId)))
				
				.filter(order->customerId==null || 
						(order.getCustomer() !=null &&
							order.getCustomer().getId().equals(customerId)))
				
				.filter(order->paymentType==null || 
						(order.getPaymentType() !=null &&
							order.getPaymentType().equals(paymentType)))
				.map(OrderMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrderByCashier(Long cashierId) {
		return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(Long id) throws Exception {
		Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("order not found by given id "+id));
		orderRepository.delete(order);
		
	}

	@Override
	public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
		
		return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay()).stream()
							.map(OrderMapper::toDto)
							.collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {
		
		return null;
	}

	@Override
	public List<OrderDto> getTop5RecentOrdersByBranchById(Long branchId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
