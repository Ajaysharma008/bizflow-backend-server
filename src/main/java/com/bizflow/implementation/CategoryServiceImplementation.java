package com.bizflow.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.constants.UserRole;
import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.CategoryMapper;
import com.bizflow.models.Category;
import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.CategoryDto;
import com.bizflow.repositories.CategoryRepository;
import com.bizflow.repositories.StoreRepository;
import com.bizflow.services.CategoryService;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

	
	private final CategoryRepository categoryRepository;
	private final UserService userService;
	private final StoreRepository storeRepository;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) throws UserException {
		
		User user = userService.getCurrentUser();
		Store store = storeRepository.findById(categoryDto.getStoreId()).get();
		
		if(checkAuthority(user, store)) {
			Category saveCategory = categoryRepository.save(CategoryMapper.toEntity(categoryDto, store));
			
			return CategoryMapper.toDto(saveCategory);
		}
		else throw new UserException("you dont have permission to create categoty");
		
	}
	

	@Override
	public List<CategoryDto> getCategoriesByStore(Long storeId) throws UserException {
		List<Category> categories = categoryRepository.findByStoreId(storeId);
		
		return categories.stream().map(category -> CategoryMapper.toDto(category)).collect(Collectors.toList());
	}

	
	@Override
	public CategoryDto upateCategory(Long categoryId, CategoryDto categoryDto) throws UserException {
		User currentUser = userService.getCurrentUser();
		
		Category category = categoryRepository.findById(categoryId).get();
		
		if(category ==null) throw new UserException("Category not found in update service");
		
		if(checkAuthority(currentUser, category.getStore())) {
			category.setName(categoryDto.getName());
			Category saveCategory = categoryRepository.save(category);
			return CategoryMapper.toDto(saveCategory);
		}else {
			throw new UserException("you dont have permission to update categoty");
		}
	}

	
	@Override
	public void deleteCategory(Long categoryId) throws UserException {
			Category category = categoryRepository.findById(categoryId).get();
			User currentUser = userService.getCurrentUser();
			
			if(category ==null) throw new UserException("Category not found");
			
			if(checkAuthority(currentUser,category.getStore())) {
				categoryRepository.delete(category);
			}
			else throw new UserException("you dont have permission to delete categoty");
			
	}
	
	
	
	private boolean checkAuthority(User user, Store store) throws UserException{
		boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
		boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
		boolean isSameStore = user. equals(store.getStoreAdmin());

		if(!(isAdmin && isSameStore) && !isManager){
			return false;
		}
		
		return true;
	}
	

}
