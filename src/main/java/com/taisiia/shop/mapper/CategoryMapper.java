package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.Category;
import com.taisiia.shop.domain.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toDao(CategoryDto categoryDto);

    CategoryDto toDto(Category category);
}
