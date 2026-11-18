package com.ecom.repository;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.CartItem;
import com.ecom.entity.Product;
import com.ecom.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByUserAndProduct(User user, Product product);

	void deleteByUserAndProduct(User user, Product product);

	List<CartItem> findByUser(User user);

	void deleteByUser(User user);
	
}
