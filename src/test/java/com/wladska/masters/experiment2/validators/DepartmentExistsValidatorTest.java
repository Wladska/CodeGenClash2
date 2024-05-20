package com.wladska.masters.experiment2.validators;
import com.wladska.masters.experiment2.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentExistsValidatorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentExistsValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValid_ReturnsFalse_WhenDepartmentIdIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void isValid_ReturnsTrue_WhenDepartmentExists() {
        Long departmentId = 1L;
        when(departmentRepository.existsById(departmentId)).thenReturn(true);

        assertTrue(validator.isValid(departmentId, null));
    }

    @Test
    void isValid_ReturnsFalse_WhenDepartmentDoesNotExist() {
        Long departmentId = 2L;
        when(departmentRepository.existsById(departmentId)).thenReturn(false);

        assertFalse(validator.isValid(departmentId, null));
    }
}
