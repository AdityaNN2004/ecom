package com.ecom.entity;

import java.math.BigDecimal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter@ToString@Getter
@Table(name="order_items")
@AttributeOverride(name="order_item_id",column=@Column(name="id"))
public class OrderItems extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	private Integer quantity;
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name="order_id",nullable = false)
	private Order order;
	@Column(name="sub_total")
	private BigDecimal subTotal; 
}
