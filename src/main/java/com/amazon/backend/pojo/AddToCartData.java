package com.amazon.backend.pojo;

import com.amazon.backend.constants.CartConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartData {
	
	@NotNull
	@Positive(message = CartConstants.ERROR_API_DATA_USER_ID)
	private int userId;
	
	@Positive(message = CartConstants.ERROR_API_DATA_PRODUCT_ID)
	private int productId;
	
	@Positive(message = CartConstants.ERROR_API_DATA_QUANTITY)
	private int quantity;

}
