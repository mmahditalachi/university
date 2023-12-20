package com.example.university.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}