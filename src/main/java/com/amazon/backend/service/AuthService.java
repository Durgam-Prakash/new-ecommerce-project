package com.amazon.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.entity.User;
import com.amazon.backend.exception.UserAlreadyExistsException;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	public User signup(SignupData signupData) {
		
		Optional<User> dbOptional = userRepository.findByEmail(signupData.getEmail());
		
		if(dbOptional.isPresent()) {
			throw new UserAlreadyExistsException(AuthConstants.USER_ALREADY_EXISTS);
		}
		
		User user = new User();
		
		user.setFirstName(signupData.getFirstName());
		user.setLastName(signupData.getLastName());
		user.setEmail(signupData.getEmail());
		user.setPasswordHash(signupData.getPassword());
		user.setPhoneNumber(signupData.getPhoneNumber());
		
		User saveUser = userRepository.save(user);
		return saveUser;
	}
}
