package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.Discount;
import com.taisiia.shop.domain.dto.DiscountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(target = "status", ignore = true)
    Discount toDao(DiscountDto discountDto);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    DiscountDto toDto(Discount discount);


    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<DiscountDto> toListDto(List<Discount> discounts);
}
