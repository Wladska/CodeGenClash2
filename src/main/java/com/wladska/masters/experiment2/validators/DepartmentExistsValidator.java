package com.wladska.masters.experiment2.validators;

import com.wladska.masters.experiment2.repositories.DepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentExistsValidator implements ConstraintValidator<DepartmentExists, Long> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void initialize(DepartmentExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long departmentId, ConstraintValidatorContext context) {
        return departmentId != null && departmentRepository.existsById(departmentId);
    }
}