package com.example.university.service;

import java.util.List;

import com.example.university.entity.Employee;
import com.example.university.model.CreateEmployeeModel;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(CreateEmployeeModel model);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}
