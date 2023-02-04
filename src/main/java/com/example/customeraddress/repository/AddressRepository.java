package com.example.customeraddress.repository;

import com.example.customeraddress.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Boolean existsByName(String name);
}
