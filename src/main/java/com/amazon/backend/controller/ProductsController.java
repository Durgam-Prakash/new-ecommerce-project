package com.amazon.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.entity.Product;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.SearchApiData;
import com.amazon.backend.service.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	
	@PostMapping("/search")
	public ResponseEntity<?>searchProduct(@Valid @RequestBody SearchApiData searchApiData) {
		
		List<Product> searchProduct = productsService.searchProduct(searchApiData);
		
		ApiResponse<List<Product>> apiResponse = new ApiResponse<>(true,AuthConstants.SEARCHED_PRODUCTS,searchProduct);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProductDetails(@PathVariable int productId){
		Product productData = productsService.getProductData(productId);
		
		ApiResponse<Product> apiResponse = new ApiResponse<>(true,AuthConstants.SUCCESS_PRODUCT_DETAILS,productData);

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
