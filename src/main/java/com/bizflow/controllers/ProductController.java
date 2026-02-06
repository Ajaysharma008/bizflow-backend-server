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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizflow.exceptions.UserException;
import com.bizflow.models.User;
import com.bizflow.payloads.dto.ProductDto;
import com.bizflow.payloads.response.ApiResponse;
import com.bizflow.services.ProductService;
import com.bizflow.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
   
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            @RequestHeader("Authorization") String jwt) throws UserException {
    	User user = userService.getUserFromJwtToken(jwt);
        ProductDto createdProduct = productService.createProduct(productDto,user);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductDto productDto,
            @RequestHeader("Authorization") String jwt
            ) throws UserException {

    	User user = userService.getUserFromJwtToken(jwt);
        productDto.setId(productId);
        ProductDto updatedProduct = productService.updateProduct(productId, productDto,user);

        return ResponseEntity.ok(updatedProduct);
    }

    // 3️⃣ Delete Product
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws UserException {
    	
    	User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(productId, user); 
        return ResponseEntity.ok(new ApiResponse(HttpStatus.ACCEPTED,"Product deleted successfully", LocalDateTime.now()));
    }

    
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getProductsByStore(
            @PathVariable Long storeId) throws UserException {

        List<ProductDto> products = productService.getProductByStoreId(storeId);
        return ResponseEntity.ok(products);
    }

    // 5️⃣ Search Products by Keyword
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @RequestParam Long storeId,
            @RequestParam String keyword) throws UserException {

        List<ProductDto> products =
                productService.SearchByKeyword(storeId, keyword);

        return ResponseEntity.ok(products);
    }
}
