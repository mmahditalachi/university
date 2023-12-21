package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.university.entity.Employee;
import com.example.university.model.CreateEmployeeModel;
import com.example.university.model.UpdateEmployeeModel;
import com.example.university.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeModel employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody UpdateEmployeeModel updatedEmployee) {
            employeeService.updateEmployee(employeeId, updatedEmployee);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
            employeeService.deleteEmployee(employeeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
