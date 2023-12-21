package com.example.university.entity;

import jakarta.persistence.*;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees;

    public Long getId() {
        return id;
    }

    public Department() {        
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees(){
        return employees;
    }
}
