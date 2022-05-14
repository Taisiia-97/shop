package com.taisiia.shop.repository;

import com.taisiia.shop.domain.DiscountStatus;
import com.taisiia.shop.domain.dao.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long>, RevisionRepository<Discount, Long, Integer> {

    Optional<Discount> findByCode(String code);

    List<Discount> findByStatus(DiscountStatus discountStatus);
}
