package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findOneByName(String name);

    Set<Category> findByIdIn(Set<Long> categoryIds);
}
