package com.wladska.masters.experiment2.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wladska.masters.experiment2.config.JwtTokenProvider;
import com.wladska.masters.experiment2.dtos.DepartmentDTO;
import com.wladska.masters.experiment2.dtos.EmployeeDTO;
import com.wladska.masters.experiment2.model.Department;
import com.wladska.masters.experiment2.model.Employee;
import com.wladska.masters.experiment2.repository.DepartmentRepository;
import com.wladska.masters.experiment2.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
@WithMockUser(roles="ADMIN")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;


    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        employee = new Employee();
        employee.setId(1L); // As this is a creation, ensure your logic does not expect an ID here unless it's for update.
        employee.setName("John Doe");
        employee.setPosition("Developer");
        employee.setSalary(100000);
        employee.setDepartment(department);

        employeeDTO = new EmployeeDTO();
        // Do not set ID if this is for creation to mimic real-world POST request for creating new entities
        employeeDTO.setName(employee.getName());
        employeeDTO.setPosition(employee.getPosition());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDepartment(new DepartmentDTO(department.getId(), department.getName()));

        // Mock the department existence check
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
    }

    @Test
    void getAllEmployees_ShouldReturnEmployees() throws Exception {
        when(employeeService.findAll()).thenReturn(List.of(employee));

        mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andDo(print());

        verify(employeeService).findAll();
    }

    @Test
    void createEmployee_ValidInput_ShouldReturnCreatedEmployee() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andDo(print());

        verify(employeeService).save(any(Employee.class));
    }
    @Test
    void getEmployeeById_ExistingId_ShouldReturnEmployee() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andDo(print());

        verify(employeeService).findById(1L);
    }

    @Test
    void updateEmployee_ExistingId_ShouldReturnUpdatedEmployee() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/api/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andDo(print());

        verify(employeeService).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_ExistingId_ShouldReturnSuccessMessage() throws Exception {
        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeService).delete(any(Employee.class));

        mockMvc.perform(delete("/api/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee deleted successfully"))
                .andDo(print());

        verify(employeeService).delete(any(Employee.class));
    }
}