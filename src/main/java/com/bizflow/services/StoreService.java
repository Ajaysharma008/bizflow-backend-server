package com.bizflow.services;

import java.util.List;

import com.bizflow.constants.StoreStatus;
import com.bizflow.exceptions.UserException;
import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.StoreDto;

public interface StoreService {
	
	StoreDto createStore(StoreDto storeDto , User user);
	StoreDto getStoreById(Long id) throws UserException;
	List<StoreDto> getAllStores() throws UserException;
	Store getStoreByAdmin() throws UserException;
	StoreDto updateStore(Long id , StoreDto storeDto) throws UserException;
	void deleteStore(long id) throws UserException;
	StoreDto getStoreByEmployee() throws UserException;
	StoreDto moderateStore(Long id,StoreStatus storeStatus) throws UserException;

}
