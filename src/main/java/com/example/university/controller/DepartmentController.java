package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.university.entity.Department;
import com.example.university.entity.Employee;
import com.example.university.model.AddEmployeeToDepartmentModel;
import com.example.university.model.DepartmentRequest;
import com.example.university.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId) {
        Optional<Department> department = departmentService.getDepartmentById(departmentId);
        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department createdDepartment = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Void> updateDepartment(
            @PathVariable Long departmentId,
            @RequestBody DepartmentRequest updatedDepartmentRequest) {
        departmentService.updateDepartment(departmentId, updatedDepartmentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployeeToDepartment(
            @RequestBody AddEmployeeToDepartmentModel model) {
        Employee addedEmployee = departmentService.addEmployeeToDepartment(model);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }
}
