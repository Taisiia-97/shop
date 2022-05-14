package com.taisiia.shop.domain.dto;

import com.taisiia.shop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private Double discountPercent;

}
