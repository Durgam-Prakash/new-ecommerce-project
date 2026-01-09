package com.amazon.backend.pojo;

import lombok.Data;

@Data
public class UpdateCartData {
	
	private int cartId;
	private int productId;
	private int quantity;
	

}
