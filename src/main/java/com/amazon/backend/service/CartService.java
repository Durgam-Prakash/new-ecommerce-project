package com.amazon.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.constants.CartConstants;
import com.amazon.backend.dto.CartDto;
import com.amazon.backend.entity.Cart;
import com.amazon.backend.entity.CartItem;
import com.amazon.backend.exception.CartItemNotFoundException;
import com.amazon.backend.exception.EmptyCartException;
import com.amazon.backend.pojo.AddToCartData;
import com.amazon.backend.pojo.UpdateCartData;
import com.amazon.backend.repository.CartItemRepository;
import com.amazon.backend.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired 
	private CartItemRepository cartItemRepository;
	
	
	
	public void addToCart(AddToCartData addToCartData) {
		Optional<Cart> cartOptional = cartRepository.findByUserId(addToCartData.getUserId());
		
		Cart cart = new Cart();
		if(cartOptional.isEmpty()) {
			
			cart.setUserId(addToCartData.getUserId());
			cart = cartRepository.save(cart);
		}
		else {
			cart = cartOptional.get();
			
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setCartId(cart.getCartId());
		cartItem.setProductId(addToCartData.getProductId());
		cartItem.setQuantity(addToCartData.getQuantity());
		cartItemRepository.save(cartItem);
	}

	
	

	
	@Transactional
	public List<CartDto> getCartData(int userId) {
		
		Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
		if(cartOptional.isEmpty() ) {
			
			throw new EmptyCartException(CartConstants.EXCEPTION_EMPTY_CART);
		}
		
		Cart cart = cartOptional.get();
		List<Object[]> cartObject = cartRepository.getCartData(cart.getCartId());
		
		
		List<CartDto> cartDataList = new ArrayList<CartDto>();
	
		for(Object[] row: cartObject) {
			
			CartDto tempCartDto = new CartDto();
			tempCartDto.setCartItemId((Integer) row[0]);
			tempCartDto.setProductId((Integer) row[1]);
			tempCartDto.setQuantity((Integer) row[2]);
			tempCartDto.setTitle((String) row[3]);
			tempCartDto.setDescription((String) row[4]);
			tempCartDto.setPrice((Double.parseDouble(String.valueOf(row[5]))));
			tempCartDto.setImages((String) row[6]);
			cartDataList.add(tempCartDto);
			
			
			
		}
		
		return cartDataList;
				
		
		
	}
	
	
	
	
	public void updateCart(int cartItemId,UpdateCartData updateCartData) {
		
		Optional<CartItem> cartOptional = cartItemRepository.findById(cartItemId);
		
		if(cartOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_CART_ITEM_NOT_FOUND,cartItemId);
		}
		
		
		CartItem cartItem = cartOptional.get();
		cartItem.setQuantity(updateCartData.getQuantity());
		cartItemRepository.save(cartItem);
		
	}
	
	
	
	
	public void deleteCartItem(int cartItemId) {
		
		Optional<CartItem> cartOptional = cartItemRepository.findById(cartItemId);
		
		if(cartOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_CART_ITEM_NOT_FOUND, cartItemId);
							
		}
		
		CartItem cartItem = cartOptional.get();
		cartItemRepository.delete(cartItem);
	}
	
	
	
	
}
