package com.employee.management;

import com.employee.management.controller.EmployeeController;
import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee(1, "John Doe", "john.doe@example.com", "Engineering", 60000);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee);

        given(employeeService.getAllEmployees()).willReturn(employees);

        mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(employee.getName()));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        given(employeeService.getEmployeeById(employee.getId())).willReturn(employee);

        mockMvc.perform(get("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()));
    }

    @Test
    public void testAddEmployee() throws Exception {
        given(employeeService.saveOrUpdateEmployee(Mockito.any(Employee.class))).willReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"department\": \"Engineering\", \"salary\": 60000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        given(employeeService.getEmployeeById(employee.getId())).willReturn(employee);
        given(employeeService.saveOrUpdateEmployee(Mockito.any(Employee.class))).willReturn(employee);

        mockMvc.perform(put("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"department\": \"Engineering\", \"salary\": 60000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()));
    }

    @Test
    public void testUpdateEmployee_NotFound() throws Exception {
        given(employeeService.getEmployeeById(employee.getId())).willReturn(null);

        mockMvc.perform(put("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"department\": \"Engineering\", \"salary\": 60000}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}