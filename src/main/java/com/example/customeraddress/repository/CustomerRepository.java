package com.example.customeraddress.repository;

import com.example.customeraddress.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);

    Boolean existsByEmail(String email);
}
