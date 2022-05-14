package com.taisiia.shop.validator;

import com.taisiia.shop.validator.impl.FileExtensionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = FileExtensionValidator.class)
public @interface FileExtensionValid {
    String message() default "File extension is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
