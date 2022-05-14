package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.User;
import com.taisiia.shop.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password",ignore = true)
    UserDto toDto(User user);

    User toDao(UserDto userDto);




}
