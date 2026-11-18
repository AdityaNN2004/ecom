package com.ecom.entity;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Product extends BaseEntity{
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stocks;
	private ProductCategory category;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="is_active")
	private Boolean isActive=true;
}
