package com.taisiia.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorDto {
    private String field;
    private String message;
}
