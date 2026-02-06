package com.bizflow.mappers;

import com.bizflow.models.Category;
import com.bizflow.models.Product;
import com.bizflow.models.Store;
import com.bizflow.payloads.dto.ProductDto;

public class ProductMapper {
	
	public static ProductDto toDto(Product product) {
		return ProductDto.builder()
				.id(product.getId())
				.name(product.getName())
				.sku(product.getSku())
				.description(product.getDescription())
				.sellingPrice(product.getSellingPrice())
				.mrp(product.getMrp())
				.brand(product.getBrand())
				.storeId(product.getStore()!=null? product.getStore().getId():null)
				.image(product.getImage())
				.createdAt(product.getCreatedAt())
				.updateAt(product.getUpdateAt())
				.categoryId(product.getCategory()!=null? product.getCategory().getId():null)
				.categoryDto(CategoryMapper.toDto(product.getCategory()))
				.build();
	}
	
	public static Product toEntity(ProductDto productDto,Store store,Category category) {
		 return Product.builder()
				 .name(productDto.getName())
				 .store(store)
				 .category(category)
				 .sku(productDto.getSku())
				 .brand(productDto.getBrand())
				 .description(productDto.getDescription())
				 .mrp(productDto.getMrp())
				 .sellingPrice(productDto.getSellingPrice())
				 .build();
		
	}

}
