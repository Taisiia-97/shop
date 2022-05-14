package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dao.Basket;
import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.domain.dao.User;
import com.taisiia.shop.exceptions.ExceededQuantityException;
import com.taisiia.shop.repository.BasketRepository;
import com.taisiia.shop.security.SecurityUtils;
import com.taisiia.shop.service.BasketService;
import com.taisiia.shop.service.ProductService;
import com.taisiia.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;


    @Override
    @Transactional
    public void add(Long productId, Integer quantity) {
        basketRepository.findByUserEmailAndProductId(SecurityUtils.getCurrentUserName(), productId)
                .ifPresentOrElse(basket -> {
                    if (basket.getProduct().getQuantity() < quantity) {
                        throw new ExceededQuantityException("Not enough quantity for product by id " + productId);
                    }
                    basket.setQuantity(quantity);

                }, () -> {
                    var product = productService.findById(productId);
                    if (product.getQuantity() < quantity) {
                        throw new ExceededQuantityException("Not enough quantity for product by id " + productId);
                    }
                    basketRepository.save(Basket.builder()
                            .product(product)
                            .quantity(quantity)
                            .user(userService.getCurrentUser())
                            .build());
                });

    }


    @Override
    @Transactional
    public void deleteAll() {
        basketRepository.deleteByUserEmail(SecurityUtils.getCurrentUserName());
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        basketRepository.deleteByUserEmailAndProductId(SecurityUtils.getCurrentUserName(), productId);
    }


    @Override
    public List<Product> getProductsBasket() {
        return basketRepository.findAllByUser(userService.getCurrentUser()).stream()
                .map(basket -> {
                    var product = basket.getProduct();
                    product.setQuantity(basket.getQuantity());
                    return product;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Basket> getBasketsByUser(User user) {
        return basketRepository.findAllByUser(user);

    }
}
