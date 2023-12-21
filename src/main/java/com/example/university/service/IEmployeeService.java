package com.example.university.service;

import java.util.List;

import com.example.university.entity.Employee;
import com.example.university.model.CreateEmployeeModel;
import com.example.university.model.UpdateEmployeeModel;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(CreateEmployeeModel model);

    Employee updateEmployee(Long id, UpdateEmployeeModel employee);

    void deleteEmployee(Long id);
}
