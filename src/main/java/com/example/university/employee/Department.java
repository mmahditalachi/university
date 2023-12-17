package com.example.university.employee;

import javax.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees;

      // Getters and setters
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
}
