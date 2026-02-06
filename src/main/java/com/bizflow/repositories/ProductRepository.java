package com.bizflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	List<Product> findByStoreId(Long storeId);
	
	
	@Query(
		    "SELECT p FROM Product p " +
		    "WHERE p.store.id = :storeId AND (" +
		    "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
		    "LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
		    "LOWER(p.sku) LIKE LOWER(CONCAT('%', :query, '%'))" +
		    ")"
    )
	
	List<Product> searchByKeyword(@Param("storeId") Long StoreId, @Param("query") String keyword);
	
}
