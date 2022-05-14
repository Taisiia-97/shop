package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.OrderDetails;
import com.taisiia.shop.domain.dto.OrderDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    OrderDetailsDto toDto(OrderDetails orderDetails);
}
