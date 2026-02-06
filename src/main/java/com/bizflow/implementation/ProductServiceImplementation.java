package com.bizflow.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.ProductMapper;
import com.bizflow.models.Category;
import com.bizflow.models.Product;
import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.ProductDto;
import com.bizflow.repositories.CategoryRepository;
import com.bizflow.repositories.ProductRepository;
import com.bizflow.repositories.StoreRepository;
import com.bizflow.services.ProductService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService{
	
	private final ProductRepository productRepository;
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto,User user) throws UserException {
		Optional<Store> storeOptional = storeRepository.findById(productDto.getStoreId());
		Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if(storeOptional.isEmpty() || categoryOptional.isEmpty()) throw new UserException("Error from Product Service");
        
        
		Product product = ProductMapper.toEntity(productDto,storeOptional.get(),categoryOptional.get()) ;
		Product savedProduct = productRepository.save(product);
		
		return ProductMapper.toDto(savedProduct);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws UserException {
		Product product = productRepository.findById(productDto.getId()).get();
		
		if(product ==null) throw new UserException("Product not found");
		
		if(productDto.getCategoryId()!=null) {
			Category category = categoryRepository.findById(productDto.getCategoryId()).get();
			if(category!=null) {
				product.setCategory(category);
			}else {
				throw new UserException("Category not found");
			}
		}
		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setSku(productDto.getSku());
		product.setImage(productDto.getImage());
		product.setMrp(productDto.getMrp());
		product.setSellingPrice(productDto.getSellingPrice());
		product.setBrand(productDto.getBrand());
		
		Product savedProduct = productRepository.save(product);
		return ProductMapper.toDto(savedProduct);
	}

	@Override
	public void deleteProduct(Long storeId,User user) throws UserException {
		 Product product = productRepository.findById(storeId).get(); 
		 productRepository.delete(product);
	 	
	}

	@Override
	public List<ProductDto> getProductByStoreId(Long storeId) throws UserException {
		 List<Product> products = productRepository.findByStoreId(storeId);
		 
		 if(products.isEmpty()) throw new UserException("Products not found");
		 
		return products.stream().map(t ->ProductMapper.toDto(t)).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> SearchByKeyword(Long storeId, String keyword) throws UserException {
		List<Product> products = productRepository.searchByKeyword(storeId, keyword);
		 
		 if(products.isEmpty()) throw new UserException("Products not found");
		 
		return products.stream().map(product ->ProductMapper.toDto(product)).collect(Collectors.toList());
		
	}

}
