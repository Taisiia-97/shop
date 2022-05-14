package com.taisiia.shop.service;

import com.taisiia.shop.domain.OrderStatus;
import com.taisiia.shop.domain.dao.OrderDetails;
import com.taisiia.shop.domain.dao.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDetails createOrder(String discountCode);

    void changeOrderStatusByOrderNumber(String orderNumber, OrderStatus status);

    Page<OrderDetails> getOrders(Pageable pageable);

    List<Product> getProductOrders(String orderNumber);


    void cancelOrder(String orderNumber);

    List<OrderDetails> getOrdersByUserEmail(String email);

    OrderDetails getLastUserOrder(String email);

    Optional<OrderDetails> getOrderByOrderNumber(String orderNumber);
}
