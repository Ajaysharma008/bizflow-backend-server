package com.bizflow.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.exceptions.UserException;
import com.bizflow.payloads.dto.InventoryDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // 1. Create Inventory
    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto)
            throws UserException {

        InventoryDto createdInventory = inventoryService.createInventory(inventoryDto);

        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }


    // 2. Update Inventory
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryDto inventoryDto)
            throws UserException {

        InventoryDto updatedInventory = inventoryService.updateInventory(id, inventoryDto);

        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }


    // 3. Delete Inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id)
            throws UserException {

        inventoryService.deleteInventory(id);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"Inventory deleted successfully",LocalDateTime.now()));
    }


    // 4. Get Inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id)
            throws UserException {

        InventoryDto inventoryDto = inventoryService.getInventoryById(id);

        return new ResponseEntity<>(inventoryDto, HttpStatus.OK);
    }


    // 5. Get Inventory by ProductId and BranchId
    @GetMapping("/product/{productId}/branch/{branchId}")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranch(
            @PathVariable Long productId,
            @PathVariable Long branchId)
            throws UserException {

        InventoryDto inventoryDto =
                inventoryService.getInventoryByProductAndBranchId(productId, branchId);

        return new ResponseEntity<>(inventoryDto, HttpStatus.OK);
    }


    // 6. Get All Inventory by BranchId
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getAllInventoryByBranchId(
            @PathVariable Long branchId)
            throws UserException {

        List<InventoryDto> inventoryList =
                inventoryService.getAllInventoryByBranchId(branchId);

        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

}
