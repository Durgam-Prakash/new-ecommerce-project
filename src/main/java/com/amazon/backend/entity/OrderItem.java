package com.amazon.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	
	private int orderId;

	private int productId;

	private int quantity;
	
	private Double price;

	private LocalDateTime createdOn = LocalDateTime.now();
	
	private LocalDateTime updatedOn = LocalDateTime.now();
	

}
