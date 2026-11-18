package com.ecom.dto;

import java.math.BigDecimal;

import com.ecom.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter@ToString
public class ProductResponse {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stocks;
	private ProductCategory category;
	private String imageUrl;
}
