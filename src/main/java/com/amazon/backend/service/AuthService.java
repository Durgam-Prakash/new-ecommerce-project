package com.amazon.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.entity.User;
import com.amazon.backend.enums.UserRole;
import com.amazon.backend.exception.InvalidPasswordException;
import com.amazon.backend.exception.UserAlreadyExistsException;
import com.amazon.backend.exception.UserNotFoundException;
import com.amazon.backend.pojo.LoginData;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Map<String, Object> signup(SignupData signupData) {

		Optional<User> dbOptional = userRepository.findByEmail(signupData.getEmail());

		if (dbOptional.isPresent()) {
			throw new UserAlreadyExistsException(AuthConstants.USER_ALREADY_EXISTS);
		}

		User user = new User();

		user.setFirstName(signupData.getFirstName());
		user.setLastName(signupData.getLastName());
		user.setEmail(signupData.getEmail());
		user.setPasswordHash(passwordEncoder.encode(signupData.getPassword()));
		user.setPhoneNumber(signupData.getPhoneNumber());
		user.setRole(UserRole.BUYER);
		user = userRepository.save(user);

		String token = jwtService.generateJwtToken(user);

		Map<String, Object> response = new HashMap<>();
		response.put("userData", user);
		response.put("token", token);

		return response;

	}
	
	
	
	
	public Map<String, Object> login(LoginData loginData) {
		
		Optional<User> dbUserOptional = userRepository.findByEmail(loginData.getEmail());
		
		if(dbUserOptional.isEmpty()) {
			throw new UserNotFoundException(AuthConstants.USER_NOT_FOUND);
		}
		
		
		User user = dbUserOptional.get();
		if( passwordEncoder.matches(loginData.getPassword(), user.getPasswordHash())==false){
			throw new InvalidPasswordException(AuthConstants.PASSWORD_INVALID);
		}
		
		String token = jwtService.generateJwtToken(user);
		
		Map<String, Object> response = new HashMap<>();
		response.put("userData", user);
		response.put("token", token);
		return response;
	}
}
