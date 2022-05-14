package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.ItemDto;
import com.taisiia.shop.domain.dto.ProductDto;
import com.taisiia.shop.mapper.ProductMapper;
import com.taisiia.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
@PreAuthorize("isAuthenticated()")
public class BasketController {
    private final BasketService basketService;
    private final ProductMapper productMapper;

    @PostMapping
    public void addProduct(@RequestBody ItemDto itemDto) {
        basketService.add(itemDto.getProductId(), itemDto.getQuantity());
    }

    @DeleteMapping
    public void deleteProducts() {
        basketService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        basketService.deleteProductById(id);
    }

    @GetMapping
    public List<ProductDto> getBasket() {
        return productMapper.toListDto(basketService.getProductsBasket());
    }
}
