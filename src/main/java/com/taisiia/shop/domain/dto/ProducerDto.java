package com.taisiia.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerDto {
    private Long id;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String name;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String country;
    @NotBlank
    @Pattern(regexp = "[A-Z][A-Z]")
    private String countryCode;

}
