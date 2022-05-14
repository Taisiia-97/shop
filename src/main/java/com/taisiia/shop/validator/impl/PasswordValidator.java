package com.taisiia.shop.validator.impl;

import com.taisiia.shop.domain.dto.UserDto;
import com.taisiia.shop.validator.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword() != null
                && userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
