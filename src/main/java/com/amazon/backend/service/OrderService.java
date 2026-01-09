package com.amazon.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.constants.OrderConstants;
import com.amazon.backend.entity.Cart;
import com.amazon.backend.entity.CartItem;
import com.amazon.backend.entity.Order;
import com.amazon.backend.entity.OrderItem;
import com.amazon.backend.entity.Product;
import com.amazon.backend.enums.OrderStatus;
import com.amazon.backend.exception.CartIdNotValidException;
import com.amazon.backend.pojo.OrderCreateApiData;
import com.amazon.backend.repository.CartItemRepository;
import com.amazon.backend.repository.CartRepository;
import com.amazon.backend.repository.OrderItemRepository;
import com.amazon.backend.repository.OrderRepository;
import com.amazon.backend.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired 
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order createOrder(OrderCreateApiData orderCreateApiData) {
		
		Optional<Cart> cartOptional = cartRepository.findByCartIdAndUserId(orderCreateApiData.getCartId(), orderCreateApiData.getUserId());
		
		if(cartOptional.isEmpty()) {
			throw new CartIdNotValidException(OrderConstants.ORDER_CART_ID_IS_INVALID, orderCreateApiData.getCartId(), orderCreateApiData.getUserId());
		}
		
		Cart cart = cartOptional.get();
		List<CartItem> cartItems = cartItemRepository.findByCartId(orderCreateApiData.getCartId());
		System.out.println(cartItems.size());
		
		Double totalPrice = 0.0;
		
		for(CartItem cartItem:cartItems) {
			System.out.println(cartItem.getProductId());
			Optional<Product> optionalProduct = productRepository.findById(cartItem.getProductId());
			Product product = optionalProduct.get();
			totalPrice = totalPrice + ( product.getPrice() * cartItem.getQuantity());
		}
		System.out.println(totalPrice);
		
		
		Order order = new Order(); 
		order.setUserId(orderCreateApiData.getUserId());
		order.setTotalAmount(totalPrice);
		order.setBillingAddressId(orderCreateApiData.getAddressId());
		order.setShippingAddressId(orderCreateApiData.getAddressId());
		order.setOrderStatus(OrderStatus.Pending);
		
		order = orderRepository.save(order);
		
		for(CartItem cartItem:cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getOrderId());
			orderItem.setProductId(cartItem.getProductId());
			Optional<Product> optionalProduct = productRepository.findById(cartItem.getProductId());
			Product product = optionalProduct.get();
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItemRepository.save(orderItem);
			
		}
		return order;        
	
	}
	
	
	
}
