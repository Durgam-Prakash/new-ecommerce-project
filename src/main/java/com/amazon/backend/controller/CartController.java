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

import com.amazon.backend.constants.CartConstants;
import com.amazon.backend.dto.CartDto;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.AddToCartData;
import com.amazon.backend.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartData addToCartData){
		
		cartService.addToCart(addToCartData);
		ApiResponse<String> apiResponse = new ApiResponse<>(true,CartConstants.SUCCESS_API_MESAGE,CartConstants.SUCCESS_API_OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		
	}
	
	
	
	@GetMapping("/view/{userId}")
	public ResponseEntity<?> getCartDetails(@PathVariable int userId){
		
		List<CartDto> dataList=  cartService.getCartData(userId);
		
		ApiResponse<List<CartDto>> apiResponse = new ApiResponse<>(true, CartConstants.SUCCESS_API_OK, dataList );

		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	} 

}
