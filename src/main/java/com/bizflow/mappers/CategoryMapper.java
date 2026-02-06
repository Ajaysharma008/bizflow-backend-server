package com.bizflow.mappers;

import com.bizflow.models.Category;
import com.bizflow.models.Store;
import com.bizflow.payloads.dto.CategoryDto;

public class CategoryMapper {
	
	public static CategoryDto toDto(Category category) {
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.storeId(category.getStore()!=null? category.getStore().getId():null)
				.build();
	}
	
	public static Category toEntity(CategoryDto categoryDto,Store store) {
		return Category.builder()
				.name(categoryDto.getName())
				.store(store)
				.build();
	}
	

}
