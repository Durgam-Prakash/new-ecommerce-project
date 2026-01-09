package com.amazon.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entity.Cart;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Integer>{

	Optional<Cart> findByUserId(int userId);
	
	@Procedure(procedureName = "sp_cart_view_based_on_cart_id")
	List<Object[]> getCartData(@Param("in_cart_id") int cartId);
	
}
