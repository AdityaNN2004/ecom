package com.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.dto.CartItemRequest;
import com.ecom.entity.CartItem;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.repository.CartItemRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	private final CartItemRepository cartItemRepository; 
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	@Override
	public boolean addCartItem(String userId, CartItemRequest request) {
		Optional<Product> productOpt = productRepository.findById(request.getProductId());
		if(productOpt.isEmpty())
			return false;
		Product product = productOpt.get();
		if(product.getStocks()<request.getQuantity())
			return false;
		Optional<User> userOpt= userRepository.findById(Long.valueOf(userId));
		if(userOpt.isEmpty())
			return false;
		User user = userOpt.get();
		CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user,product);
		if(existingCartItem!=null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity());
			existingCartItem.setPrice(existingCartItem.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			cartItemRepository.save(existingCartItem);
		}else {
			CartItem newCartItem = new CartItem();
			newCartItem.setUser(user);
			newCartItem.setProduct(product);
			newCartItem.setQuantity(request.getQuantity());
			newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
			cartItemRepository.save(newCartItem);
		}
		return true;
	}
	@Override
	public boolean deleteCartItem(String userId, Long productId) {
	    Optional<Product> productOpt = productRepository.findById(productId);
	    Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
	    if(userOpt.isPresent() && productOpt.isPresent()) {
	    	cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
	    	return true;
	    }
	    return false;
	}
	@Override
	public List<CartItem> fetchCartItems(String userId) {
	    return userRepository.findById(Long.valueOf(userId))
	            .map(cartItemRepository::findByUser)
	            .orElseGet(List::of);
	}
	@Override
	public void clearCart(String userId) {
		userRepository.findById(Long.valueOf(userId)).ifPresent(user->cartItemRepository.deleteByUser(user));
	}


}
