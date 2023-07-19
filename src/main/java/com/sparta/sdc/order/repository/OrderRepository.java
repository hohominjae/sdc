package com.sparta.sdc.order.repository;

import com.sparta.sdc.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
