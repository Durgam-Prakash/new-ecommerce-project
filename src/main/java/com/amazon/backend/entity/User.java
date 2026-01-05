package com.amazon.backend.entity;

import java.time.LocalDateTime;

import com.amazon.backend.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	private String passwordHash;
	private String phoneNumber;
	private LocalDateTime createdOn =LocalDateTime.now();
	private LocalDateTime updatedOn =LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private UserRole role=UserRole.BUYER;
	

}
