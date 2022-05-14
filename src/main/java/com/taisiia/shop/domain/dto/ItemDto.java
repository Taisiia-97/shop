package com.taisiia.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class ItemDto {
    @NotNull
    @NotBlank
    private Long productId;
    @NotNull
    @Positive
    private Integer quantity;
}
