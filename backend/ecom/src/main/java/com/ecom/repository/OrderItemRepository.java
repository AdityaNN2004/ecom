package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Order;

public interface OrderItemRepository extends JpaRepository<Order, Long> {

}
