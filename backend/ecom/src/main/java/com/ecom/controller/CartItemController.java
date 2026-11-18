package com.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CartItemRequest;
import com.ecom.entity.CartItem;
import com.ecom.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartItemController {
	private final CartItemService cartItemService;
	@PostMapping
	public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,@RequestBody CartItemRequest request){
		if(cartItemService.addCartItem(userId,request)) {
			return ResponseEntity.badRequest().body("Product out of stock or Product not found or User not found");
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@DeleteMapping("/items/{productId}")
	public ResponseEntity<Void> removeFromCart(@RequestHeader("X-User-ID") String userId,@PathVariable Long productId){
		boolean deleted = cartItemService.deleteCartItem(userId,productId);
		return deleted ? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
	}
	@GetMapping
	public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-ID") String userId){
		return  ResponseEntity.ok(cartItemService.fetchCartItems(userId));
	}
}
