package com.ecom.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter@ToString@NoArgsConstructor@AllArgsConstructor
@Table(name="orders")
@AttributeOverride(name="order_id",column = @Column(name="id"))
public class Order extends BaseEntity {
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	@ManyToOne
	@JoinColumn(name="",nullable = false)
	private User user;
	@Enumerated(EnumType.STRING)
	private OrderStatus status=OrderStatus.PENDING;
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
	@Column(name="order_items")
	private List<OrderItems> orderItems=new ArrayList<>();
}
