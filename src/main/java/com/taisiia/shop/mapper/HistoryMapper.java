package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.Discount;
import com.taisiia.shop.domain.dao.Product;
import com.taisiia.shop.domain.dao.User;
import com.taisiia.shop.domain.dto.DiscountDto;
import com.taisiia.shop.domain.dto.ProductDto;
import com.taisiia.shop.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class,ProducerMapper.class})
public interface HistoryMapper {

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.firstName", target = "firstName")
    @Mapping(source = "entity.lastName", target = "lastName")
    @Mapping(source = "entity.email", target = "email")
    @Mapping(source = "requiredRevisionNumber",target = "revisionNumber")
    UserDto mapToDto(Revision<Integer, User> revision);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name",target = "name")
    @Mapping(source = "entity.description",target = "description")
    @Mapping(source = "entity.price",target = "price")
    @Mapping(source = "entity.producer.id",target = "producerId")
    @Mapping(source = "entity.categories",target = "categories")
    @Mapping(source = "entity.photoUrl",target = "photo")
    @Mapping(source = "entity.producer",target = "producer")
    @Mapping(source = "requiredRevisionNumber",target = "revisionNumber")
    ProductDto mapToProductDto(Revision<Integer, Product> revision);


    @Mapping(source = "entity.id",target = "id")
    @Mapping(source = "entity.name",target = "name")
    @Mapping(source = "entity.code",target = "code")
    @Mapping(source = "entity.percent",target = "percent")
    @Mapping(source = "entity.startDate",target = "startDate")
    @Mapping(source = "entity.endDate",target = "endDate")
    @Mapping(source = "entity.status",target = "status")
    @Mapping(source = "requiredRevisionNumber",target = "revisionNumber")
    DiscountDto mapToDiscountDto(Revision<Integer, Discount> revision);
}
