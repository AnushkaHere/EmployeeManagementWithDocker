package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Employee Controller", description = "This is Employee API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @Operation(
            summary="getAllEmployees",
            description = "To fetch all the employees",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500",description = "Internal Server Error")}
        )
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    @Operation(
            summary="getEmployeeById",
            description = "To fetch specific employee by Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "500",description = "Internal Server Error")}
    )
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    @Operation(summary="addEmployee", description = "To create a new employee",
                responses= {
                        @ApiResponse(responseCode = "200", description = "Success"),
                        @ApiResponse(responseCode = "201", description = "New employee created")
    })
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.saveOrUpdateEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    @Operation(summary="updateEmployee", description = "To update employee info",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "201", description = "Employee info updated"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            })
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setSalary(employee.getSalary());

            Employee updatedEmployee = employeeService.saveOrUpdateEmployee(existingEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/employees/{id}")
    @Operation(summary="deleteEmployee", description = "To delete an employee info by id",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            })
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }
}
