package com.bizflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizflow.models.Store;
import com.bizflow.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	List<User> findByStore(Store store);
	List<User> findByBranchId(Long branchId);
	

}
