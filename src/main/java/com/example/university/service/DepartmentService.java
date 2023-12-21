package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.university.entity.Department;
import com.example.university.entity.Employee;
import com.example.university.exceptions.NotFoundException;
import com.example.university.model.AddEmployeeToDepartmentModel;
import com.example.university.model.DepartmentRequest;
import com.example.university.repository.DepartmentRepository;
import com.example.university.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;    
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,
     EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;               
        this.employeeRepository = employeeRepository;       
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + departmentId));

        List<Employee> employees = new ArrayList<>(department.getEmployees());

        return employees;
    }


    public Department createDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        return departmentRepository.save(department);
    }

    public void updateDepartment(Long id, DepartmentRequest updatedDepartmentRequest) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            throw new NotFoundException("Department with ID " + id + " not found");
        
        Department existingDepartment = optionalDepartment.get();
        existingDepartment.setName(updatedDepartmentRequest.getName());
        departmentRepository.save(existingDepartment);            
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Employee addEmployeeToDepartment(AddEmployeeToDepartmentModel model) {
        Department department = departmentRepository.findById(model.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + model.getDepartmentId()));

        Employee employee = employeeRepository.findById(model.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + model.getEmployeeId()));

        employee.getDepartments().add(department);

        departmentRepository.save(department);

        return employee;
    }
}
