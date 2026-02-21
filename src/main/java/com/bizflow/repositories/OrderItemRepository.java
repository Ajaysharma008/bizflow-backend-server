package com.bizflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizflow.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
