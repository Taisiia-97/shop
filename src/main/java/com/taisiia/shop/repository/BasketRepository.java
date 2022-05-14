package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Basket;
import com.taisiia.shop.domain.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    void deleteByUserEmail(String email);

    void deleteByUserEmailAndProductId(String userName, Long id);

    List<Basket> findAllByUser(User user);

   Optional <Basket> findByUserEmailAndProductId(String userName, Long id);

}
