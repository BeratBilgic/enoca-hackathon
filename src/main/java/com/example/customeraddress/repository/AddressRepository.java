package com.example.customeraddress.repository;

import com.example.customeraddress.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressById(Long Id);
}
