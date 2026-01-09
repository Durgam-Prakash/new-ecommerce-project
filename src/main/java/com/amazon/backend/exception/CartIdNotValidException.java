package com.amazon.backend.exception;

public class CartIdNotValidException extends RuntimeException{

	private int cartId=0;
	private int userId=0;
	public CartIdNotValidException(String message,int cartId, int userId) {
		super(message);
		this.cartId=cartId;
		this.userId=userId;
	}
	
	public int getCartId() {
		return this.cartId;
	}
	
	public int getUserId() {
		return this.userId;
	}
}
