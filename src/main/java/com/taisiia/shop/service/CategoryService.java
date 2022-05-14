package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CategoryService {
    Category save(Category category);

    Category update(Category category, Long id);

    Category findById(Long id);

    void delete(Long id);

    Page<Category> getPage(Pageable pageable);

    Category getCategoryByName(String name);

    Set<Category> getCategories(Set<Long> categoriesIds);
}
