package com.wladska.masters.experiment2.validators;
import com.wladska.masters.experiment2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class  DepartmentExistsValidator implements ConstraintValidator<DepartmentExists, Long> {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void initialize(DepartmentExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long departmentId, ConstraintValidatorContext context) {
        if (departmentId == null) {
            return false; // treat null as invalid
        }
        return departmentRepository.existsById(departmentId);
    }
}