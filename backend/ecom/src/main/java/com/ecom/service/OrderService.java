package com.ecom.service;

import java.util.Optional;

import com.ecom.dto.OrderResponse;

public interface OrderService {

	Optional<OrderResponse> placeOrder(String userId);

}
