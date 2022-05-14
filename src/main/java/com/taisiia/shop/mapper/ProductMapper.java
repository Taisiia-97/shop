package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.domain.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDto toDto(Product product);

    @Mapping(target = "categories", ignore = true)
    Product toDao(ProductDto productDto);

    List<ProductDto> toListDto(List<Product> products);


}
