package com.taisiia.shop.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String name;
    @NotBlank
    @Length(min = 3, max = 255)
    private String description;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    private Long producerId;
    @NotNull
    @Positive
    private Long quantity;
    @Size(min = 1)
    private Set<Long> categoryIds;
    private Set<CategoryDto> categories;
    private String photo;
    private ProducerDto producer;
    private Integer revisionNumber;

}
