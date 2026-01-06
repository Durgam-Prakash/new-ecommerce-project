package com.amazon.backend.exception;

public class InvalidOtpForPasswordResetException extends RuntimeException {

	public InvalidOtpForPasswordResetException(String message) {
		super(message);
	}
}
