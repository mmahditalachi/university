package com.example.university.repository;

import com.example.university.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Custom queries or methods can be added here if needed
}
