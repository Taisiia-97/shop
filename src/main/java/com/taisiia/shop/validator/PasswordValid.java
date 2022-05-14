package com.taisiia.shop.validator;

import com.taisiia.shop.validator.impl.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValid {
    String message() default "Password and password confirm not the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
