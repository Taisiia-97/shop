package com.taisiia.shop.service;

import com.taisiia.shop.domain.DiscountStatus;
import com.taisiia.shop.domain.dao.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    Discount create(Discount discount, String status);

    Discount update(Discount discount, String status, Long id);

    Discount getById(Long id);

    Page<Discount> getDiscountPage(Pageable pageable);

    List<Discount> getDiscountsByStatus(DiscountStatus discountStatus);

    void deleteById(Long id);

    void changeStatus(Long id,DiscountStatus discountStatus);

    Optional<Discount> findByDiscountCode(String code);
}
