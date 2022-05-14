package com.taisiia.shop.domain.dao;

import com.taisiia.shop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String orderNumber;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    private User user;
    private Double discountPercent;

}
