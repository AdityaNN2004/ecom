package com.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter@Getter@ToString
@NoArgsConstructor
public class CartItemRequest {
	private Long productId;
	private int quantity;
}
