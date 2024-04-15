package com.wladska.masters.experiment2.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DepartmentExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentExists {
    String message() default "Department does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}