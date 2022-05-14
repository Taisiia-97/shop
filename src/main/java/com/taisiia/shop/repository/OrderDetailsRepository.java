package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    Optional<OrderDetails> findByOrderNumber(String orderNumber);

    Page<OrderDetails> findByUserEmail(String email, Pageable pageable);


    List<OrderDetails> findAllByUserEmail(String email);

    List<OrderDetails> findByUserEmailOrderByOrderDate(String email);


}
