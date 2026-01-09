package com.amazon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constants.AddressConstants;
import com.amazon.backend.entity.Address;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.AddressAddApiData;
import com.amazon.backend.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> adddAddress(@RequestBody AddressAddApiData addressAddApiData){
		Address address = addressService.addAddress(addressAddApiData);
		ApiResponse<?> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_API_OK,address);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
