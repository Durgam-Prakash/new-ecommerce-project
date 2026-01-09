package com.amazon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constants.OrderConstants;
import com.amazon.backend.entity.Order;
import com.amazon.backend.payload.ApiResponse;
import com.amazon.backend.pojo.OrderCreateApiData;
import com.amazon.backend.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/create") 
	public ResponseEntity<?> createOrder(@RequestBody OrderCreateApiData orderCreateApiData) {
		Order order = orderService.createOrder(orderCreateApiData);

		ApiResponse<?> apiResponse = new ApiResponse<>(true, OrderConstants.SUCCESS_ORDER_CREATED, order);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
