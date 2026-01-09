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
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;

	private int cartId;

	private int productId;

	private int quantity;

	private LocalDateTime createdOn = LocalDateTime.now();

	private LocalDateTime updatedOn = LocalDateTime.now();

}
