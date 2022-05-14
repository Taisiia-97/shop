package com.taisiia.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taisiia.shop.domain.DiscountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private Long id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    private String code;
    @NotNull
    @Positive
    private Double percent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDate;
    @NotBlank
    private String status;
    private Integer revisionNumber;

}
