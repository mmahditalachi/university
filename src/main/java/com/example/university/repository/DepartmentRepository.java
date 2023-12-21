package com.example.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.university.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
}