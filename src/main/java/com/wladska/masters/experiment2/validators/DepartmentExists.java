package com.wladska.masters.experiment2.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DepartmentExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentExists {
    String message() default "Department does not exist"; // Default error message

    Class<?>[] groups() default {}; // Required for validation constraints

    Class<? extends Payload>[] payload() default {}; // Required for validation constraints
}
