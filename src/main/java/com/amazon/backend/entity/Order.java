package com.amazon.backend.entity;

import java.time.LocalDateTime;

import com.amazon.backend.enums.OrderStatus;

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
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int userId;
	private Double totalAmount;
	private int billingAddressId;
	private int shippingAddressId;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedOn = LocalDateTime.now();
	

}
