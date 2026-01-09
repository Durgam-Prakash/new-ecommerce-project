package com.amazon.backend.exception;

public class CartItemNotFoundException extends RuntimeException{

	
	private int cartItemId;
	public CartItemNotFoundException(String message,int cartItemId) {
		super(message);
		this.cartItemId=cartItemId;
	}
	
	public int getCartItemId() {
		return this.cartItemId;
	}
}
