package com.amazon.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	
	List<CartItem> findByCartId(int cartId);

}
