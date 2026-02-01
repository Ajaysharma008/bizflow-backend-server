package com.bizflow.payloads.dto;

import com.bizflow.constants.StoreType;
import com.bizflow.models.StoreContact;
import com.bizflow.models.User;

import lombok.Data;

@Data
public class StoreDto {
	
	
    private String brand;
    private String description;
    private StoreType storeType;
    private User storeAdmin = new User();
    private StoreContact storeContact;
}
