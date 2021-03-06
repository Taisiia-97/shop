package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

    List<Order> findByOrderDetailsOrderNumber(String orderNumber);
}
