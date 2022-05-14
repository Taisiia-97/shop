package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

    Product save(Product product, MultipartFile photo, Long producerId, Set<Long> categoryIds) throws IOException;

    Product update(Product product, MultipartFile photo, Long producerId, Set<Long> categories, Long id);

    Product findById(Long id);

    void delete(Long id);

    Page<Product> getPage(Pageable pageable);
    //function for password

    List<Product> findByName(String name);

    List<Product> findByCategory(String categoryName);

    List<Product> findByProducentUsingProcecentName(String producentName);

    List<Product> findByProcecentUsingCountryCode(String countryCode);

    List<Product> findByPriceRange(Double minPrice, Double maxPrice);

    List<Product> findByDescription(String text);

}
