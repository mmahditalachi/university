package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.university.entity.Employee;
import com.example.university.exceptions.EmployeeNotFoundException;
import com.example.university.model.CreateEmployeeModel;
import com.example.university.model.UpdateEmployeeModel;
import com.example.university.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService  implements IEmployeeService {
      private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElse(null); // You may want to handle not-found cases differently
    }

    @Override
    public Employee createEmployee(CreateEmployeeModel model) {
        Employee emp = new Employee(model.getFirstName(), model.getLastName(),
         model.getJobTitle(), model.getAge(), model.getGender(), model.getImage());         
        return employeeRepository.save(emp);
    }

    @Override
    public Employee updateEmployee(Long id, UpdateEmployeeModel updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        Employee existingEmployee = optionalEmployee.get();

        // Update employee details
        existingEmployee.setJobTitle(updatedEmployee.getJobTitle());
        existingEmployee.setAge(updatedEmployee.getAge());
        existingEmployee.setGender(updatedEmployee.getGender());
        existingEmployee.setImage(updatedEmployee.getImage());

        // Save the updated employee
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
