package com.bizflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{
	List<Branch> findByStoreId(Long storeId);
}
