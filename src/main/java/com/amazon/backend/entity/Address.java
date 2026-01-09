package com.amazon.backend.entity;

import java.time.LocalDateTime;

import com.amazon.backend.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private int userId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pinCode;
	private String country;
	private double latitude;
	private double longitude;
	private boolean isDefault;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	private LocalDateTime createdOn = LocalDateTime.now();

	private LocalDateTime updatedOn = LocalDateTime.now();

}
