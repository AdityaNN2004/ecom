package com.ecom.entity;

import java.math.BigDecimal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter@Getter@ToString
@AllArgsConstructor
@Entity
@AttributeOverride(name="cart_id",column = @Column(name="id"))
public class CartItem extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	private int quantity;
	private BigDecimal price;
	
}
