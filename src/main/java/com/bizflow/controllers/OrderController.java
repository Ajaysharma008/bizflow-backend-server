package com.bizflow.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.constants.OrderStatus;
import com.bizflow.constants.PaymentType;
import com.bizflow.payloads.dto.OrderDto;
import com.bizflow.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 1. Create Order
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    // 2. Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // 3. Get Orders by Branch with filters
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrdersByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus status
    ) throws Exception {

        List<OrderDto> orders = orderService.getOrdersByBranch(
                branchId,
                customerId,
                cashierId,
                paymentType,
                status
        );

        return ResponseEntity.ok(orders);
    }

    // 4. Get Orders by Cashier
    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCashier(@PathVariable Long cashierId) {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    // 5. Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) throws Exception {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    // 6. Get Today Orders by Branch
    @GetMapping("/branch/{branchId}/today")
    public ResponseEntity<List<OrderDto>> getTodayOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
    }

    // 7. Get Orders by Customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Long customerId) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    // 8. Get Top 5 Recent Orders by Branch
    @GetMapping("/branch/{branchId}/recent")
    public ResponseEntity<List<OrderDto>> getRecentOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchById(branchId));
    }

}