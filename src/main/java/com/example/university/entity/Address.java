package com.example.university.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String phoneNumber;    


    public Address() {        
    }

    public Address(String title, String description, String phoneNumber, Employee employee) {
        this.title = title;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.employee = employee;
    }
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Constructors, getters, setters...

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
