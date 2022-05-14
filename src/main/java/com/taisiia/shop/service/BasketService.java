package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.Basket;
import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.domain.dao.User;

import java.util.List;

public interface BasketService {

    void add(Long productId, Integer quantity);

    void deleteAll();

    void deleteProductById(Long productId);

    List<Product> getProductsBasket();

    List<Basket> getBasketsByUser(User user);


}
