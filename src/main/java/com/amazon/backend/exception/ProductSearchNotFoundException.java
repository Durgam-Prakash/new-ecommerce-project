package com.amazon.backend.exception;

public class ProductSearchNotFoundException extends RuntimeException {

	public ProductSearchNotFoundException(String message) {
		super(message);
	}
}
