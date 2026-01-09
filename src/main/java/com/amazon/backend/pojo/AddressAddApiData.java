package com.amazon.backend.pojo;

import com.amazon.backend.enums.AddressType;

import lombok.Data;

@Data
public class AddressAddApiData {

	private int userId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
    private String pinCode;
    private String country;
    private double latitude;
    private double longitude;
    private boolean isDefault;
    private AddressType addressType;
	
}
