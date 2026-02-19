package com.bizflow.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Order;
import com.bizflow.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerId(Long customerId);
	List<Order> findByBranchId(Long brachId);
	List<Order> findByCashierId(Long cashierId);
	List<Order> findByBranchIdAndCreatedAtBetween(
			Long branchId,LocalDateTime from,LocalDateTime to);
	
	List<Order> findByCashierAndCreatedAtBetween(
			User cashier, LocalDateTime from, LocalDateTime to
			);
	
	List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);
}
