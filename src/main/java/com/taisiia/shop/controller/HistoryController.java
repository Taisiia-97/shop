package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.DiscountDto;
import com.taisiia.shop.domain.dto.ProductDto;
import com.taisiia.shop.domain.dto.UserDto;
import com.taisiia.shop.mapper.HistoryMapper;
import com.taisiia.shop.repository.DiscountRepository;
import com.taisiia.shop.repository.ProductRepository;
import com.taisiia.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
@PreAuthorize("isAuthenticated()&&hasRole('ADMIN')")
public class HistoryController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    private final HistoryMapper historyMapper;

    @GetMapping("/{userId}")
    public Page<UserDto> getUserHistory(@PathVariable Long userId,
                                        @RequestParam int page,
                                        @RequestParam int size) {
        return  userRepository.findRevisions(userId, PageRequest.of(page, size)).map(historyMapper::mapToDto);

    }
    @GetMapping("/product/{productId}")
    public Page<ProductDto> getProductHistory(@PathVariable Long productId,
                                              @RequestParam int page,
                                              @RequestParam int size){
        return productRepository.findRevisions(productId,PageRequest.of(page,size)).map(historyMapper::mapToProductDto);
    }


    @GetMapping("/discount/{discountId}")
    public Page<DiscountDto> getDiscountHistory(@PathVariable Long discountId,
                                                @RequestParam int page,
                                                @RequestParam int size){
        return discountRepository.findRevisions(discountId,PageRequest.of(page,size)).map(historyMapper::mapToDiscountDto);
    }


}
