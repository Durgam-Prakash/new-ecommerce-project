package com.amazon.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazon.backend.constants.ExceptionConstants;
import com.amazon.backend.payload.ApiResponse;

@RestControllerAdvice
public class GlobalException {
	
	

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex){
			Map<String, String> errorsMap = new HashMap<>();
			ex.getBindingResult().getFieldErrors().forEach(error->{
				errorsMap.put(error.getField(), error.getDefaultMessage());
				
				});
			
			ApiResponse<Map<String, String>> response = new ApiResponse<>(false,ExceptionConstants.API_FAILED,errorsMap);
			
			 
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST); 
			
		}
		
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex){
			
			ApiResponse<String> response = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_REUQEST,ex.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException ex){
			
			ApiResponse<String> response = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_REUQEST,ex.getMessage());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			
		}
		
		
		@ExceptionHandler(InvalidPasswordException.class)
		public ResponseEntity<ApiResponse<String>> handleInvalidPasswordException(InvalidPasswordException ex){
			
			ApiResponse<String> response = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_REUQEST,ex.getMessage());
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}

}
