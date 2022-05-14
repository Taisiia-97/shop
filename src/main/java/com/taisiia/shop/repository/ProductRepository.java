package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, RevisionRepository<Product, Long, Integer> {

    List<Product> findAllByName(String name);
//???

    List<Product> findByCategories_Name(String categoryName);

    List<Product> findByDescriptionContaining(String description);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByProducer_Name(String producerName);

    //???
    List<Product> findAllByProducerCountry(String country);



}
