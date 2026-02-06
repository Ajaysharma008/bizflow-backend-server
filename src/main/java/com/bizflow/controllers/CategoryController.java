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
import com.bizflow.payloads.dto.CategoryDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 1️⃣ Create Category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto) throws UserException {

        CategoryDto createdCategory =
                categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // 2️⃣ Get Categories by Store
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStore(
            @PathVariable Long storeId) throws UserException {

        List<CategoryDto> categories =
                categoryService.getCategoriesByStore(storeId);

        return ResponseEntity.ok(categories);
    }

    // 3️⃣ Update Category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryDto categoryDto) throws UserException {

        CategoryDto updatedCategory =
                categoryService.upateCategory(categoryId, categoryDto);

        return ResponseEntity.ok(updatedCategory);
    }

    // 4️⃣ Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long categoryId) throws UserException {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.ACCEPTED,
                        "Category deleted successfully",
                        LocalDateTime.now()
                )
        );
    }
}
