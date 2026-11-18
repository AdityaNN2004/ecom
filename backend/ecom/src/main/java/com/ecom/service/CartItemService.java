package com.ecom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.CartItemRequest;
import com.ecom.entity.CartItem;

public interface CartItemService {

	boolean addCartItem(String userId, CartItemRequest request);

	boolean deleteCartItem(String userId, Long productId);

	List<CartItem> fetchCartItems(String userId);

	void clearCart(String userId);

}
