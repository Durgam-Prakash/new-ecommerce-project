package com.amazon.backend.exception;

public class AddressNotFoundException extends RuntimeException {

	private int userId;
	private int addressId;
	
	public AddressNotFoundException(String message,int addressId, int userId) {
		super(message);
		this.addressId=addressId;
		this.userId=userId;
		
	}
	
	
	public int getAddressId() {
		return this.addressId;
	}
	
	public int getUserId() {
		return this.userId;
	}
	

	

}
