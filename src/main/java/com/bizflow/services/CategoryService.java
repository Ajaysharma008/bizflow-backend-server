package com.bizflow.services;

import java.util.List;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto) throws UserException;
	
	List<CategoryDto> getCategoriesByStore(Long storeId) throws UserException;
	
	CategoryDto upateCategory(Long categoryId , CategoryDto categoryDto) throws UserException;
	
	void deleteCategory(Long categoryId) throws UserException;

}
