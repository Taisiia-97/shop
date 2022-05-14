package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dao.Category;
import com.taisiia.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements com.taisiia.shop.service.CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(Category category, Long id) {
        Category categoryToUpdate = findById(id);
        categoryToUpdate.setName(category.getName());
        return categoryToUpdate;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> getPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findOneByName(name);
    }

    @Override
    public Set<Category> getCategories(Set<Long> categoriesIds) {
        return categoryRepository.findByIdIn(categoriesIds);
    }
}
