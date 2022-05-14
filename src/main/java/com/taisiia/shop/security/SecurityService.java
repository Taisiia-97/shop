package com.taisiia.shop.security;

import com.taisiia.shop.service.OrderService;
import com.taisiia.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final OrderService orderService;

    public boolean hasAccessToUser(Long userId) {
        return userService.getCurrentUser().getId().equals(userId);
    }


    public boolean hasOrderDetailsToUser(String orderNumber) {
        return orderService.getOrderByOrderNumber(orderNumber).isPresent();

    }
}
