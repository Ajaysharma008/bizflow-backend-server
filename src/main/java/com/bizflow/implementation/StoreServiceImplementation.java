package com.bizflow.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bizflow.constants.StoreStatus;
import com.bizflow.exceptions.UserException;
import com.bizflow.mappers.StoreMapper;
import com.bizflow.models.Store;
import com.bizflow.models.StoreContact;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.StoreDto;
import com.bizflow.repositories.StoreRepository;
import com.bizflow.repositories.UserRepository;
import com.bizflow.services.StoreService;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImplementation implements StoreService {

    private final UserRepository userRepository;



	private final StoreRepository storeRepository;
	private final UserService userService;


	
	@Override
	public StoreDto createStore(StoreDto storeDto, User user) {
		System.out.println("555555555555555555555555555555555555555555555555");

		Store store = StoreMapper.toEntity(storeDto,user);
		System.out.println(store);
		return StoreMapper.toDto(storeRepository.save(store));
	}

	@Override
	public StoreDto getStoreById(Long id) throws UserException {
		Optional<Store> store = storeRepository.findById(id);
		if(store.isEmpty()) throw new UserException("Store not found by given id "+id);
		return StoreMapper.toDto(store.get());
	}

	@Override
	public List<StoreDto> getAllStores() throws UserException{
		List<Store> allStore = storeRepository.findAll();
		List<StoreDto> allStoresDto = allStore.stream()
				.map(store -> StoreMapper.toDto(store))
				.collect(Collectors.toList());
		return allStoresDto;
	}

	@Override
	public Store getStoreByAdmin() throws UserException{
		
		Optional<Store> store = storeRepository.findById(userService.getCurrentUser().getId());
		
		if(store.isEmpty()) throw new UserException("Store not found by given id ");
		return store.get();
	}

	@Override
	public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException{
		Store currentStore = storeRepository.findById(id).get();
		if(currentStore == null) throw new UserException("Store not found by given id "+id);
		
		currentStore.setBrand(storeDto.getBrand());
		currentStore.setDescription(storeDto.getDescription());
		
		if(storeDto.getStoreType() !=null) {
			currentStore.setStoreType(storeDto.getStoreType());
		}
		
		if(storeDto.getStoreContact() !=null) {
			StoreContact newStoreContact = StoreContact.builder()
					.address(storeDto.getStoreContact().getAddress())
					.phone(storeDto.getStoreContact().getPhone())
					.email(storeDto.getStoreContact().getEmail())
					.build();
			
			currentStore.setStoreContact(newStoreContact);
		}
		
		Store updatedStore = storeRepository.save(currentStore);
		
		return StoreMapper.toDto(updatedStore);
	}

	@Override
	public void deleteStore(long id) throws UserException{
		Store store= getStoreByAdmin();
		
		if(store !=null) {
			storeRepository.deleteById(id);
		}
		if(store ==null) {
			throw new UserException("Store not found by given id ");
		}
		
	}

	@Override
	public StoreDto getStoreByEmployee() throws UserException{
		Optional<Store> store = storeRepository.findById(userService.getCurrentUser().getId());
		
		if(store.isEmpty()) throw new UserException("Store not foun");
		return StoreMapper.toDto(store.get());
	}

	@Override
	public StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException {
		Store store = storeRepository.findById(id).get();
		
		if(store==null) throw new UserException("Store not found with this id ");
		
		store.setStoreStatus(storeStatus);
		
		
		return StoreMapper.toDto(storeRepository.save(store));
	}
	
	

}
