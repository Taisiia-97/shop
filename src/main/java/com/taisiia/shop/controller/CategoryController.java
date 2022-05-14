package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.CategoryDto;
import com.taisiia.shop.mapper.CategoryMapper;
import com.taisiia.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()&&hasRole('ADMIN')")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.findById(id));
    }

    @PostMapping
    public CategoryDto saveCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryService.save(categoryMapper.toDao(categoryDto)));
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto, @PathVariable Long id) {
        return categoryMapper.toDto(categoryService.update(categoryMapper.toDao(categoryDto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping
    public Page<CategoryDto> getCategoryPage(@RequestParam int page, @RequestParam int size) {
        return categoryService.getPage(PageRequest.of(page, size)).map(categoryMapper::toDto);
    }

    @GetMapping("/name")
    public CategoryDto findCategoryByName(@RequestParam String name) {
        return categoryMapper.toDto(categoryService.getCategoryByName(name));
    }
}
