package com.taisiia.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.shop.validator.PasswordValid;
import com.taisiia.shop.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordValid(groups = Create.class)
public class UserDto {
    private Long id;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String firstName;
    @NotBlank
    @Length(min = 3, max = 255)
    private String lastName;
    @NotBlank
    @Length(min = 3, max = 255)
    @Email
    private String email;
    @NotBlank(groups = Create.class)
    @Length(min = 12, max = 20,groups = Create.class)
    private String password;
    private String confirmPassword;
    private Integer revisionNumber;

}
