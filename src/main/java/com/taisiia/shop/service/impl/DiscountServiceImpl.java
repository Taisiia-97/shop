package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.DiscountStatus;
import com.taisiia.shop.domain.dao.Discount;
import com.taisiia.shop.repository.DiscountRepository;
import com.taisiia.shop.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    @Override
    public Discount create(Discount discount, String status) {
        discount.setStatus(DiscountStatus.safeValueOf(status));
        return discountRepository.save(discount);
    }

    @Override
    @Transactional
    public Discount update(Discount discount, String status, Long id) {
        var toUpdate = getById(id);
        toUpdate.setName(discount.getName());
        toUpdate.setCode(discount.getCode());
        toUpdate.setPercent(discount.getPercent());
        toUpdate.setStatus(DiscountStatus.safeValueOf(status));
        toUpdate.setEndDate(discount.getEndDate());
        toUpdate.setStartDate(discount.getStartDate());
        return toUpdate;
    }

    @Override
    public Discount getById(Long id) {
        return discountRepository.getById(id);
    }

    @Override
    public Page<Discount> getDiscountPage(Pageable pageable) {
        return discountRepository.findAll(pageable);
    }

    @Override
    public List<Discount> getDiscountsByStatus(DiscountStatus discountStatus) {
        return discountRepository.findByStatus(discountStatus);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changeStatus(Long id, DiscountStatus discountStatus) {
        getById(id).setStatus(discountStatus);

    }

    @Override
    public Optional<Discount> findByDiscountCode(String code) {
        return discountRepository.findByCode(code);
    }
}
