package com.bizflow.services;

import java.util.List;

import com.bizflow.exceptions.UserException;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.ProductDto;

public interface ProductService {
	ProductDto createProduct(ProductDto productDto,User user) throws UserException;
	
	ProductDto updateProduct(Long id, ProductDto productDto,User user) throws UserException;
	void deleteProduct(Long storeId, User user) throws UserException;
	List<ProductDto> getProductByStoreId(Long storeId) throws UserException;
	List<ProductDto> SearchByKeyword(Long storeId,String keyword) throws UserException;
	
}
