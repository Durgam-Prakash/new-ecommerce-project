package com.amazon.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.entity.User;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.ForgotPasswordSendOtp;
import com.amazon.backend.pojo.LoginData;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> createAccount(@Valid @RequestBody SignupData signupData){
		Map<String, Object> data = authService.signup(signupData);
		
		ApiResponse<User> apiResponse = new ApiResponse<>(true,AuthConstants.SUCCESS_ACCOUNT_CREATED,(User)data.get("userData"));
		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + data.get("token").toString());
		
		return  ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginData loginData) {
		
		Map<String, Object> login = authService.login(loginData);
		
		
		ApiResponse<User> apiResponse = new ApiResponse<>(true,AuthConstants.SUCCESS_LOGIN,(User)login.get("userData"));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorozation","Bearer " + login.get("token").toString());
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
				
	}
	
	
	
	@PostMapping("/forgot-password/send-otp")
	public ResponseEntity<?> sendOtp(@Valid @RequestBody ForgotPasswordSendOtp sendOtp){
		
		authService.forgotPasswordSendOtp(sendOtp);
		
		ApiResponse<String> apiResponse = new ApiResponse<String>(true,AuthConstants.SUCCESS_OTP_SENT_TO_EMAIL,"");

		 return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	
	
	
	
}
