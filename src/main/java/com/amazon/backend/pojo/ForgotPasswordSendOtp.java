package com.amazon.backend.pojo;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.constants.GenericConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPasswordSendOtp {

	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
	@Pattern(regexp = GenericConstants.EMAIL_REGX,message = AuthConstants.ERROR_EMAIL_IS_NOT_VALID)
	private String email; 
	
} 
