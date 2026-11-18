package com.ecom.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDto {
	private Long id;
	private Long productId;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal subTotal;
}
