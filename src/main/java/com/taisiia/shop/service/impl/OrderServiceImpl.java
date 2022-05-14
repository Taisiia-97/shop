package com.taisiia.shop.service.impl;

import com.taisiia.shop.config.AppConfig;
import com.taisiia.shop.domain.OrderStatus;
import com.taisiia.shop.domain.dao.Order;
import com.taisiia.shop.domain.dao.OrderDetails;
import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.helper.AppHelper;
import com.taisiia.shop.repository.OrderDetailsRepository;
import com.taisiia.shop.repository.OrderRepository;
import com.taisiia.shop.security.SecurityUtils;
import com.taisiia.shop.service.BasketService;
import com.taisiia.shop.service.DiscountService;
import com.taisiia.shop.service.OrderService;
import com.taisiia.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final BasketService basketService;
    private final UserService userService;
    private final DiscountService discountService;
    private final AppHelper appHelper;


    @Override
    @Transactional
    public OrderDetails createOrder(String discountCode) {
        var user = userService.getCurrentUser();
        var orderDetails = OrderDetails.builder()
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.CREATED)
                .orderNumber(appHelper.generateRandomString())
                .user(user)
                .build();
        discountService.findByDiscountCode(discountCode)
                .ifPresentOrElse(discount -> orderDetails.setDiscountPercent(discount.getPercent()),
                        () -> orderDetails.setDiscountPercent(0.0));
        orderDetailsRepository.save(orderDetails);
        orderRepository.saveAll(basketService.getBasketsByUser(user).stream()
                .map(basket -> Order.builder()
                        .price(basket.getProduct().getPrice())
                        .product(basket.getProduct())
                        .quantity(basket.getQuantity())
                        .orderDetails(orderDetails)
                        .build())
                .collect(Collectors.toList()));
        basketService.deleteAll();

        return orderDetails;
    }

    @Override
    @Transactional
    public void changeOrderStatusByOrderNumber(String orderNumber, OrderStatus status) {
        OrderDetails orderDetails = orderDetailsRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new EntityNotFoundException(orderNumber));
        orderDetails.setOrderStatus(status);

    }


    @Override
    public Page<OrderDetails> getOrders(Pageable pageable) {
        return orderDetailsRepository.findByUserEmail(SecurityUtils.getCurrentUserName(), pageable);

    }

    @Override
    public List<Product> getProductOrders(String orderNumber) {
        return orderRepository.findByOrderDetailsOrderNumber(orderNumber).stream()
                .map(order -> {
                    var product = order.getProduct();
                    product.setQuantity(order.getQuantity());
                    product.setPrice(order.getPrice());
                    return product;
                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void cancelOrder(String orderNumber) {
        orderRepository.deleteAll(orderRepository.findByOrderDetailsOrderNumber(orderNumber));
        var order = orderDetailsRepository
                .findByOrderNumber(orderNumber).orElseThrow(EntityNotFoundException::new);

        orderDetailsRepository.delete(order);
    }

    @Override
    public List<OrderDetails> getOrdersByUserEmail(String email) {
        return orderDetailsRepository.findAllByUserEmail(email);
    }

    @Override
    public OrderDetails getLastUserOrder(String email) {
        List<OrderDetails> orders = orderDetailsRepository.findByUserEmailOrderByOrderDate(email);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException();
        }
        int i = orders.size() - 1;
        return orders.get(i);
    }

    @Override
    public Optional<OrderDetails> getOrderByOrderNumber(String orderNumber) {
        return orderDetailsRepository.findByOrderNumber(orderNumber);
    }

}
