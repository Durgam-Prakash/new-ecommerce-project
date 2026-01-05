package com.amazon.backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazon.backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret.key}")
	private String jwtSecretKey;
	
	
	private int JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
	
	
	private Key generateSecurityKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
	}
	
	
	
	public String generateJwtToken(User userData) {
		Date tokenGeneratedTime = new Date();
		Date expiryDate = new Date(tokenGeneratedTime.getTime() + JWT_TOKEN_VALIDITY);
		
		
		Map<String, Object> tokenData = new HashMap<>();
		tokenData.put("id", userData.getUserId());
		tokenData.put("name", userData.getFirstName());
		tokenData.put("email", userData.getEmail());
		tokenData.put("role", userData.getRole());
		
		
		
		String jwtToken = Jwts.builder()
		.claims()
		.add(tokenData)
		.and()
		.subject(userData.getEmail())
		.issuedAt(tokenGeneratedTime)
		.expiration(expiryDate)
		.signWith(generateSecurityKey())
		.compact();
		
		return jwtToken;
		
	}
	
	
	
	public Claims getJwtClaims(String token) {
		SecretKey secretKey = new SecretKeySpec(jwtSecretKey.getBytes(), "HmacSHA256");
		
		Claims claims = Jwts.parser()
		.verifyWith(secretKey)
		.build()
		.parseSignedClaims(token)
		.getPayload();
		
		return claims;
		
	
	}
	
	
	
	public Boolean  verifyJwtToken (String token) {
		Claims claims = getJwtClaims(token);
		Boolean isValid = claims.getExpiration().after(new Date());
		return isValid;
	}
	
	
	
	
	

}
