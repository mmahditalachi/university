package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.university.employee.EmployeeRepository;
import com.example.university.exceptions.EmployeeNotFoundException;
import com.example.university.model.Employee;
import com.example.university.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
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

            // Update addresses
            Set<Address> existingAddresses = existingEmployee.getAddresses();
            
            if (updatedEmployee.getAddresses() != null) {
                existingAddresses.clear(); // Remove existing addresses
                for (Address updatedAddress : updatedEmployee.getAddresses()) {
                    existingEmployee.addAddress(updatedAddress);
                }
            }

            // Save the updated employee
            employeeRepository.save(existingEmployee);
            return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
