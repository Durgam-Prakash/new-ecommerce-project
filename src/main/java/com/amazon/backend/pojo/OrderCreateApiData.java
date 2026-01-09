package com.amazon.backend.pojo;

import lombok.Data;

@Data
public class OrderCreateApiData {
	
	private int userId;
	private int cartId;
	private int addressId;

}
