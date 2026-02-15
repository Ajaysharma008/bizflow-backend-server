package com.bizflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	Optional<Inventory> findByProductIdAndBranchId(Long productId,Long branchId);
	List<Inventory> findByBranchId(Long branchId);
}
