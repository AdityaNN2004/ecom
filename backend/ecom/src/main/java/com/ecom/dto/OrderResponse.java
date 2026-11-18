package com.ecom.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ecom.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
	private Long id;
	private BigDecimal totalAmount;
	private OrderStatus status;
	private List<OrderItemDto> orderItems;
	private LocalDateTime createdAt;
}
