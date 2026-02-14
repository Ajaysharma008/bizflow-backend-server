package com.bizflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	Store findByStoreAdminId(Long adminId);
}
