package com.amazon.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.entity.Address;
import com.amazon.backend.pojo.AddressAddApiData;
import com.amazon.backend.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	public Address addAddress(AddressAddApiData addressAddApiData) {
		
		Address address = new Address();
		address.setUserId(addressAddApiData.getUserId());
		address.setAddressLine1(addressAddApiData.getAddressLine1());
		address.setAddressLine2(addressAddApiData.getAddressLine2());
		address.setCity(addressAddApiData.getCity());
		address.setState(addressAddApiData.getState());
		address.setPinCode(addressAddApiData.getPinCode());
		address.setCountry(addressAddApiData.getCountry());
		address.setLatitude(addressAddApiData.getLatitude());
		address.setLongitude(addressAddApiData.getLongitude());
		address.setDefault(addressAddApiData.isDefault());
		address.setAddressType(addressAddApiData.getAddressType());
		
		Address saveAddress = addressRepository.save(address);
		return saveAddress;
		
	}
	
	public List<Address> getAddress(int userId){
		return addressRepository.findByUserId(userId);
	}
} 
