package com.amazon.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{userId}/view")
	public ResponseEntity<?> getAddress(@PathVariable int userId){
		List<Address> address = addressService.getAddress(userId);
		ApiResponse<?> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_API_OK,address);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse); 
				
	}
	
	
	@DeleteMapping("/{addressId}/{userId}/delete")
	public ResponseEntity<?> deleteAddress(@PathVariable int addressId, @PathVariable int userId){
		addressService.deleteAddress(addressId, userId);
		ApiResponse<?> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_API_OK,AddressConstants.SUCCESS_ADDRESS_IS_DELETED);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse); 
	} 

}
