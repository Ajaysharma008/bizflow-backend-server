 package com.bizflow.mappers;

import com.bizflow.models.Store;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.StoreDto;

public class StoreMapper {

    // Entity → DTO
    public static StoreDto toDto(Store store) {
        if (store == null) {
            return null;
        }

        StoreDto dto = new StoreDto();
        dto.setBrand(store.getBrand());
        dto.setDescription(store.getDescription());
        dto.setStoreType(store.getStoreType());
        dto.setStoreAdmin(store.getStoreAdmin());
        dto.setStoreContact(store.getStoreContact());

        return dto;
    }

    // DTO → Entity
    public static Store toEntity(StoreDto dto,User storeAdmin) {
        if (dto == null) {
            return null;
        }

        Store store = new Store();
        store.setBrand(dto.getBrand());
        store.setDescription(dto.getDescription());
        store.setStoreType(dto.getStoreType());
        store.setStoreAdmin(storeAdmin);
        store.setStoreContact(dto.getStoreContact());

        // NOTE:
        // id, createdAt, updatedAt, storeStatus
        // ye sab JPA + lifecycle handle karega

        return store;
    }
}
