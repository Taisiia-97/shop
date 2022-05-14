package com.taisiia.shop.controller;

import com.taisiia.shop.domain.DiscountStatus;
import com.taisiia.shop.domain.dto.DiscountDto;
import com.taisiia.shop.mapper.DiscountMapper;
import com.taisiia.shop.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()&&hasRole('ADMIN')")
public class DiscountController {

    private final DiscountService discountService;
    private final DiscountMapper discountMapper;

    @PostMapping
    public DiscountDto createDiscount(@RequestBody DiscountDto discountDto) {
        return discountMapper.toDto(discountService.create(discountMapper.toDao(discountDto), discountDto.getStatus()));
    }

    @PutMapping("/{id}")
    public DiscountDto updateDiscount(@RequestBody DiscountDto discountDto, @PathVariable Long id) {
        return discountMapper.toDto(discountService.update(discountMapper.toDao(discountDto), discountDto.getStatus(), id));
    }

    @GetMapping("/{id}")
    public DiscountDto findDiscountById(@PathVariable Long id) {
        return discountMapper.toDto(discountService.getById(id));
    }

    @GetMapping
    public Page<DiscountDto> discountPage(@RequestParam int page, @RequestParam int size) {
        return discountService.getDiscountPage(PageRequest.of(page, size)).map(discountMapper::toDto);
    }


    @GetMapping("/status")
    public List<DiscountDto> getDiscountsByStatus(@RequestParam DiscountStatus status) {
        return discountMapper.toListDto(discountService.getDiscountsByStatus(status));

    }

    @PutMapping("/status/{id}")
    public void changeDiscountStatusById(@PathVariable Long id, @RequestParam DiscountStatus status) {
        discountService.changeStatus(id, status);
    }

    @DeleteMapping("{id}")
    public void deleteDiscountById(@PathVariable Long id) {
        discountService.deleteById(id);
    }
}
