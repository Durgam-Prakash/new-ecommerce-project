package com.amazon.backend.dto;

import lombok.Data;

@Data
public class CartDto {

	private int cartItemId;
	private int productId;
	private int quantity;
	private String title;
	private String description;
	private double price;
	private String images;
	
	
}
