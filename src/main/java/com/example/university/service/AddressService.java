package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.university.repository.AddressRepository;
import com.example.university.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import com.example.university.entity.Address;
import com.example.university.entity.Employee;
import com.example.university.exceptions.AddressNotFoundException;
import com.example.university.exceptions.EmployeeNotFoundException;
import com.example.university.model.CreateAddressModel;
import com.example.university.model.UpdateAddressModel;

@Service
public class AddressService {
     private final AddressRepository addressRepository;     
     private final EmployeeRepository employeeRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository,
    EmployeeRepository employeeRepository) {
            this.addressRepository = addressRepository;
            this.employeeRepository = employeeRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address createAddress(Long employeeId, CreateAddressModel model) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
        } else {
            Address address = new Address(model.getTitle(), model.getDescription(),
                 model.getPhoneNumber(), optionalEmployee.get());
            Employee employee = optionalEmployee.get();
            address.setEmployee(employee);
            return addressRepository.save(address);            
        }
    }

    public void updateAddress(Long id, UpdateAddressModel updatedAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            throw new AddressNotFoundException("Address with ID" + id + " not found");
        }
        Address existingAddress = optionalAddress.get();
        existingAddress.setTitle(updatedAddress.getTitle());
        existingAddress.setDescription(updatedAddress.getDescription());
        existingAddress.setPhoneNumber(updatedAddress.getPhoneNumber());
        addressRepository.save(existingAddress);        
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
