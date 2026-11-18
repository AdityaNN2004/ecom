package com.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.dto.OrderItemDto;
import com.ecom.dto.OrderResponse;
import com.ecom.entity.CartItem;
import com.ecom.entity.Order;
import com.ecom.entity.OrderItems;
import com.ecom.entity.OrderStatus;
import com.ecom.entity.User;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final CartItemService cartItemService;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	@Override
	public Optional<OrderResponse> placeOrder(String userId) {
		List<CartItem> cartItems = cartItemService.fetchCartItems(userId);
		if(cartItems.isEmpty()) {
			return Optional.empty();
		}
		Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
		if(userOptional.isEmpty()) {
			return Optional.empty();
		}
		User user = userOptional.get();
		BigDecimal totalPrice = cartItems.stream()
				.map(CartItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		Order order = new Order();
		order.setUser(user);
		order.setTotalAmount(totalPrice);
		order.setStatus(OrderStatus.CONFIRMED);
		List<OrderItems> orderItems = cartItems.stream()
										.map(item -> new OrderItems(
												item.getProduct(),
												item.getQuantity(),
												item.getPrice(),
												order,
												totalPrice
												))
										.toList();
		order.setOrderItems(orderItems);
		Order savedOrder = orderRepository.save(order);
		cartItemService.clearCart(userId);
		Optional.of(mapToOrderResponse(savedOrder));
		return null;
	}
	private OrderResponse mapToOrderResponse(Order order) {
		return new OrderResponse(
				order.getId(),
				order.getTotalAmount(),
				order.getStatus(),
				order.getOrderItems().stream()
						.map(orderItem -> new OrderItemDto(
							orderItem.getId(),
							orderItem.getProduct().getId(),
							orderItem.getQuantity(),
							orderItem.getPrice(),
							orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
						)).toList(),
				order.getCreatedOn()
				);
	}

}
