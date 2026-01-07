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
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String title;
	
	private String description;
	
	private Double price;
	
	private int stock;
	
	private int categoryId;
	
	private String images;
	
	private LocalDateTime createdOn =LocalDateTime.now();
	
	private LocalDateTime updatedOn =LocalDateTime.now();
	

}
