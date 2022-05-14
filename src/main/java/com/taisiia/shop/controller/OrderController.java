package com.taisiia.shop.controller;

import com.taisiia.shop.domain.OrderStatus;
import com.taisiia.shop.domain.dto.OrderDetailsDto;
import com.taisiia.shop.domain.dto.ProductDto;
import com.taisiia.shop.mapper.OrderDetailsMapper;
import com.taisiia.shop.mapper.ProductMapper;
import com.taisiia.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailsMapper orderDetailsMapper;
    private final ProductMapper productMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public OrderDetailsDto createOrder(@RequestParam(required = false,defaultValue = "No code") String discountCode) {
        return orderDetailsMapper.toDto(orderService.createOrder(discountCode));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/status")
    public void changeOrderStatus(@RequestParam String orderNumber, @RequestParam OrderStatus orderStatus) {
        orderService.changeOrderStatusByOrderNumber(orderNumber, orderStatus);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/page")
    public Page<OrderDetailsDto> getOrderPage(@RequestParam int page, @RequestParam int size) {
        return orderService.getOrders(PageRequest.of(page, size)).map(orderDetailsMapper::toDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order_products")
    public List<ProductDto> getProductOrders(@RequestParam String orderNumber) {
        return productMapper.toListDto(orderService.getProductOrders(orderNumber));
    }

    @PreAuthorize("isAuthenticated() && (@securityService.hasOrderDetailsToUser(#orderNumber) || hasRole('ADMIN'))")
    @PutMapping("/cancel")
    public void cancelOrder(@RequestParam String orderNumber) {
        orderService.cancelOrder(orderNumber);
    }

}
