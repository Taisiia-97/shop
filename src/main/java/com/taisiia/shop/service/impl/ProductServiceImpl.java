package com.taisiia.shop.service.impl;

import com.taisiia.shop.config.properties.FilePropertiesConfig;
import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.helper.FileHelper;
import com.taisiia.shop.repository.ProductRepository;
import com.taisiia.shop.service.CategoryService;
import com.taisiia.shop.service.ProducerService;
import com.taisiia.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProducerService producerService;
    private final CategoryService categoryService;
    private final FileHelper fileHelper;
    private final FilePropertiesConfig filePropertiesConfig;


    @Override
    @CachePut(cacheNames = "products", key = "#result.id")
    public Product save(Product product, MultipartFile photo, Long producerId, Set<Long> categoriesIds) throws IOException {
        if (photo != null) {
            Path path = Paths.get(filePropertiesConfig.getProduct(), product.getName() + ".png");
            product.setPhotoUrl(path.toString());
            fileHelper.copy(photo.getInputStream(),path);
        }

        product.setProducer(producerService.findById(producerId));
        product.setCategories(categoryService.getCategories(categoriesIds));
        return productRepository.save(product);
    }

    @Override
    @Transactional
    @CachePut(cacheNames = "products", key = "#result.id")
    public Product update(Product product, MultipartFile photo, Long producerId, Set<Long> categoriesIds, Long id) {
        Product productToUpdate = findById(id);
        productToUpdate.setProducer(producerService.findById(producerId));
        productToUpdate.setCategories(categoryService.getCategories(categoriesIds));
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setName(product.getName());
        return productToUpdate;
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#id")
    public Product findById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#id")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Product> findByProducentUsingProcecentName(String producentName) {
        return null;
    }

    @Override
    public List<Product> findByProcecentUsingCountryCode(String countryCode) {
        return null;
    }

    @Override
    public List<Product> findByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByDescription(String text) {
        return productRepository.findByDescriptionContaining(text);
    }

}
