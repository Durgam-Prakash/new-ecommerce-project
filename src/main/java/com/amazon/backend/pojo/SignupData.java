package com.amazon.backend.pojo;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.constants.GenericConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupData {
	
	@NotNull(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
	@Size(min = 3, message = AuthConstants.ERROR_FIRST_NAME_MIN_3_CHARS)
	private String firstName;
	
	@NotNull(message = AuthConstants.ERROR_LAST_NAME_REQUIRED)
	@Size(min = 3,message = AuthConstants.ERROR_LAST_NAME_MIN_3_CHARS)
	private String lastName;
	
	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
	@Pattern(regexp = GenericConstants.EMAIL_REGX,message = AuthConstants.ERROR_EMAIL_IS_NOT_VALID)
	private String email; 
	
	@NotNull(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
	@Size(min = 8, message = AuthConstants.ERROR_PASSWORD_MIN_8_CHARS)
	private String password;
	
	@NotNull(message = AuthConstants.ERROR_PHONE_NUMBER_REQUIRED)
	private String phoneNumber;
	

}
